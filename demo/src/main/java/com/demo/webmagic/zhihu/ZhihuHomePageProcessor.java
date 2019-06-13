package com.demo.webmagic.zhihu;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;

public class ZhihuHomePageProcessor implements PageProcessor {
    // 设置编码 ，超时时间，重试次数，
    private Site site = Site.me().setRetryTimes(10).setSleepTime(5000).setTimeOut(5000)
            .addCookie("Domain", "zhihu.com")
            .addCookie("z_c0", "2|1:0|10:1514447557|4:z_c0|92:Mi4xd1NYSkJBQUFBQUFBa0NHYkp4cm5EQ2NBQUFDRUFsVk54QzlzV2dCalJ4LWRUOHhLMVJLMmFndTZrc3ZBdF9xRkxn|ba62c51f070c150b87cfd3aa0b882a258887d326320cd21e04bd441399b70d28")
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");


    private static final String URL_answer = "https://www\\.zhihu\\.com/question/\\d+/answer/\\d+";

    private static final String URL_ANSWERS = "https://www\\.zhihu\\.com/people/.*/answers";

    private static String userName = "ling-long-xie-seng";

    @Override
    public void process(us.codecraft.webmagic.Page page) {
        if (page.getUrl().regex(URL_ANSWERS).match()) {
            int total = 20;
            int time = total / 20;
            page.setCharset("UTF-8");
            for (int i = 0; i <= time; i++) {
                int offset = i * 20;
                int limit = total < (i + 1) * 20 ? total : ((i + 1) * 20 - 1);
                String url = "https://www.zhihu.com/api/v4/members/" + userName + "/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Cmark_infos%2Ccreated_time%2Cupdated_time%2Creview_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cvoting%2Cis_author%2Cis_thanked%2Cis_nothelp%2Cupvoted_followees%3Bdata%5B*%5D.author.badge%5B%3F(type%3Dbest_answerer)%5D.topics&offset=" + offset + "&limit=" + limit + "&sort_by=created";
                page.addTargetRequest(url);
            }
        } else if (page.getUrl().regex(URL_answer).match()) {
            List<String> urlList = page.getHtml().xpath("//div[@class=RichContent-inner]//img/@src").all();
            String filePath = "E:\\备份\\images\\" + userName;
            Random random = new Random();
            urlList.forEach(url -> {
                if (!url.contains("data")) {
                    try {
                        System.out.println("url--->" + url);
                        downloadPicture(url, filePath, Integer.toString(random.nextInt() >>> 1)
                                + url.substring(url.lastIndexOf("."), url.length()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            List<String> answerIds = new JsonPathSelector("$.data[*].id").selectList(page.getRawText());
            List<String> questionIds = new JsonPathSelector("$.data[*].question.id").selectList(page.getRawText());
            for (int i = 0; i < answerIds.size(); i++) {
                String answerUrl = "https://www.zhihu.com/question/" + questionIds.get(i) + "/answer/" + answerIds.get(i);
                page.addTargetRequest(answerUrl);
            }
        }
    }

    @Override
    public Site getSite() {

        return site;
    }

    public static void downloadPicture(String urlString, String savePath, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File file = new File(savePath + "/" + filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

    }


//    public static void main(String[] args) {
//        userName = "xiao-xian-nu-albee";
//        //个人主页的网址
//        String url = "https://www.zhihu.com/people/ling-long-xie-seng/answers";
//        Spider.create(new ZhihuHomePageProcessor())
//                .addUrl(url)
////				.addPipeline(new GetquestionUrlPipeline())
//                .thread(10)
//                .run();
//
//    }
}