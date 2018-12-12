package com.demo.webmagic.github;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;


public class GithubPageProcesser implements PageProcessor {

    //设置站点的超时时间，重试次数,,,这里属于站点的信息配置
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    //爬虫的代码逻辑，抽取逻辑的代码实现
    @Override
    public void process(Page page) {


        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        //把链接加入到待抓取队列中
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").addPipeline(new JsonFilePipeline("E:\\webmagic\\")).thread(5).run();
    }
}
