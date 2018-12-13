package com.magicstory.magic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StoryPageProcesser implements PageProcessor {

    //设置站点的超时时间，重试次数,,,这里属于站点的信息配置
    private Site site = Site.me().setTimeOut(10000).setSleepTime(100).setRetryTimes(3).setCharset("gbk");

    private static String URL = null;
    private static String path = null;
    private String fileName = null;
    private static File file = null;

    public void setURL(String URL) {
        StoryPageProcesser.URL = URL;
    }

    public void setPath(String path) {
        StoryPageProcesser.path = path;
    }

    @Override
    public void process(Page page) {


        if (page.getUrl().toString().equals(URL)) {
            int count = 0;
            fileName = page.getHtml().xpath("//div[@id='maininfo']/div/h1/text()").toString() + ".txt";
            System.out.println("文件绝对路径名--->" + path + fileName);

            file = new File(path + fileName);
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            List<String> list = page.getHtml().links().all();

            for (String str : list) {
                if (str.matches(URL + "[0-9]{5,9}.html")) {
                    //System.out.println("url--->" + str);
                    page.addTargetRequest(str);
                    count++;
                }
            }
            System.out.println("当前页面的url_total = " + count);
        }
        if (page.getUrl().regex(URL + "[0-9]{5,9}.html").match()) {
            try {
                downLoadText(page, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void downLoadText(Page page, File file) throws IOException {
        String title = page.getHtml().xpath("//div[@class='bookname']/h1/text()").toString();
        String content = page.getHtml().xpath("//div[@id='content']/text()").toString();

//        content = content.substring(0, content.length() - 33);

        //System.out.println("text-->" + title + "   content-->" + content);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(title);
        bw.newLine();
        bw.newLine();
        bw.write(content);
        bw.newLine();
        bw.newLine();
        bw.flush();
        bw.close();
        System.out.println(title + "  缓存完成！！");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        String url = "https://www.37zw.net/3/3578/";
        String url = "https://www.37zw.net/1/1291/";
        String path = "F:\\webmagic\\";
        StoryPageProcesser processer = new StoryPageProcesser();
        processer.setURL(url);
        processer.setPath(path);
        Spider.create(processer).addUrl(url).run();
    }
}
