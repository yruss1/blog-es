package com.xu.blog.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 11582
 */
public class HtmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtils.class);

    /**
     * 禁止实例化
     */
    private HtmlUtils() {
        throw new IllegalStateException("禁止实例化");
    }

    /**
     * 过滤HTML标签输出文本
     *
     * @param inputString 原字符串
     * @return 过滤后字符串
     */
    public static String Html2Text(String inputString) {
        if (StringUtils.isEmpty(inputString)) {
            return "";
        }

        // 含html标签的字符串
        String htmlStr = inputString.trim();
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_space;
        Matcher m_space;
        Pattern p_escape;
        Matcher m_escape;

        try {
            // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

            // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";

            // 定义空格回车换行符
            String regEx_space = "\\s*|\t|\r|\n";

            // 定义转义字符
            String regEx_escape = "&.{2,6}?;";

            // 过滤script标签
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");

            // 过滤style标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");

            // 过滤html标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");

            // 过滤空格回车标签
            p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
            m_space = p_space.matcher(htmlStr);
            htmlStr = m_space.replaceAll("");

            // 过滤转义字符
            p_escape = Pattern.compile(regEx_escape, Pattern.CASE_INSENSITIVE);
            m_escape = p_escape.matcher(htmlStr);
            htmlStr = m_escape.replaceAll("");

            textStr = htmlStr;

        } catch (Exception e) {
            logger.info("Html2Text:{}", e.getMessage());
        }

        // 返回文本字符串
        return textStr;
    }

}
