/**
 *
 */
package com.cangqu.gallery.server.base.model.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: 客户端接收的结果对象
 */
@ApiModel(value = "BaseResult", description = "", discriminator = "")
public class BaseResultVo implements Serializable {


    private static final long serialVersionUID = -7978635757653362784L;

    @ApiModelProperty(value = "返回码，0表示成功，非0表示失败", notes = "")
    private int resultCode;

    @ApiModelProperty(value = "返回消息，成功为success，失败为具体失败信息", notes = "")
    private String resultMessage;

    // 返回数据
    @ApiModelProperty(value = "返回数据", notes = "")
    private Object resultData;

    public BaseResultVo() {
    }

    public BaseResultVo(String resultMessage, Object resultData) {
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }

    public BaseResultVo(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "BaseResultVo [resultCode=" + resultCode + ", resultMessage=" + resultMessage
                + ", resultData=" + resultData + "]";
    }

}