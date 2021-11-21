package com.example.qrcode.controller;

import com.example.qrcode.bean.Order;
import com.example.qrcode.utils.QrCodeUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author relax
 * @version 1.0
 * @功能描述：
 * @date 2021/10/8 22:51
 */
@RestController
public class NodeController {


    @PostMapping("/node")
    public Object nodeTest(@RequestBody JsonNode nodes) {

        return new Order(nodes);

    }

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "lwh768561";
        // 嵌入二维码的图片路径
        String imgPath = "C:/xxx.jpg";
        // 生成的二维码的路径及名称
        String destPath = "C:/qrCode/lwh768561.jpg";
        //生成二维码
        QrCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QrCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

}
