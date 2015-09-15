package org.daikaixian.jspider.spider;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.daikaixian.jspider.mailsender.MailSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kaishui on 15-8-30.
 */
public class JSpider implements Job{
    //文件存储路径
    private static String FILE_PATH = "/home/kaishui/Documents/";
    //登陆页面
    private static String LOGIN_PAGE = "http://eschedule.shanghaigm.com/JQ/DunsLogin.jsp";
    //登录后父窗口的首页
    private static String INDEX_PAGE = "http://eschedule.shanghaigm.com/JQ/jsp/common/index.jsp";

    //未读订单页面
    private static String UNREAD_PUS_PAGE_DOMAIN ="http://eschedule.shanghaigm.com/JQ/pus_/pusSearch.do"
    				+ "?method=listDunsUnReadPus&__SORT_TAG_NAME_SORT_COLUMN=__cGxhbkFycml2ZVRpbWU=__&__SORT_TAG_NAME_SORT_ORDER=DESC";
    //已接受订单页面
    private static String ACCEPTED_PUS_PAGE_DOMAIN = "http://eschedule.shanghaigm.com/JQ/pus_/pusSearch.do"
            + "?method=listDunsAcceptPus&__SORT_TAG_NAME_SORT_COLUMN=__cGxhbkFycml2ZVRpbWU=__&__SORT_TAG_NAME_SORT_ORDER=DESC";

    SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd aHH:mm:ss:SSS");
    //邮件发送工具
    MailSender mailSender = new MailSender();

    public JSpider(){

    }

    public  void fetchDatda() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        System.out.println(dateFormatGmt.format(new Date())+"开始");
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //浏览器选项设置
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        //加载登录页面
        final HtmlPage page=webClient.getPage(LOGIN_PAGE);
        //设置js超时时间
        webClient.waitForBackgroundJavaScript(1000*5);
        webClient.setJavaScriptTimeout(0);
        //获取登录表单的相关元素
        final HtmlForm form = page.getFormByName("formname");
        final HtmlTextInput unam = form.getInputByName("loginId");
        unam.setValueAttribute("mensuo0000");
        final HtmlPasswordInput psd = form.getInputByName("pwd");
        psd.setValueAttribute("Aa123456");
        final List<?> imgs = page.getElementsByTagName("img");
        System.out.println(imgs.size());
        //登录按钮是第14张个图片
        final HtmlImage image = (HtmlImage) imgs.get(13);
        System.out.println(image.getSrcAttribute());

        //点击提交表单
        HtmlPage page2 = null;
        try{
            page2 = (HtmlPage) image.click();//点击登陆
            webClient.waitForBackgroundJavaScript(1000*10L);
            System.out.println("!!!!second  page!!!");
        }catch(Exception e){
            System.out.println(e);
        }
        //page2是一个弹窗
        System.out.println(page2.asXml());
        System.out.println("*********分割线****************");

        //String domain = "http://eschedule.shanghaigm.com/JQ/wdf/wdf.do?method=toDoc&id=1015664&state=3";
        //String domain = "http://eschedule.shanghaigm.com/JQ/pus_/pus.do?method=showPusDetail&sid=9052601";
        final HtmlPage page4 = webClient.getPage(ACCEPTED_PUS_PAGE_DOMAIN);
        System.out.println(page4.asXml());
        final HtmlTable table = page4.getHtmlElementById("pusListTab");
        //循环爬取并发送邮件,暂时未做去重操作
        for(int i = 1;i <table.getRowCount();i++){ //从1开始,跳过表头
            HtmlTableRow row = table.getRow(i);
            System.out.println("Found row");
            if(row.getCells().size() > 3){
                HtmlAnchor href = (HtmlAnchor) row.getCell(3).getElementsByTagName("a").get(0);
                HtmlPage pageX = href.click();
                //获取下载超链接
                HtmlAnchor anchorAttachment = pageX.getAnchors().get(2);
                String fileName = "file_"+i+".doc";
                File file = ouputDocFile(anchorAttachment,fileName);
                file.getName();
                // 设置发件人地址、收件人地址和邮件标题
                mailSender.setAddress("waterdkx@163.com", "935961250@qq.com", "一个带附件的JavaMail邮件");
                // 设置要发送附件的位置和标题
                mailSender.setAffix(FILE_PATH + fileName, file.getName());
                // 设置smtp服务器以及邮箱的帐号
                mailSender.send("smtp.163.com", "waterdkx", "yijiushuige");
            }

        }

//		//加载iframe
//		HtmlPage frame=(HtmlPage)page2.getFrameByName("zhuti").getEnclosedPage();
    }

    public File ouputDocFile(HtmlElement anchorAttachment,String fileName) throws IOException {
        InputStream is;
        File ret = new File(FILE_PATH+fileName);
        is = anchorAttachment.click().getWebResponse().getContentAsStream();
        try {
            OutputStream out = new FileOutputStream(ret);
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            is.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
            return ret;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("fetch");
        try {
            fetchDatda();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
