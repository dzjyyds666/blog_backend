package com.Aaron.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;
    private String message;
    private Object data;

    public static Result Success(Integer code, String message, Object data){
        return new Result (code,message,data);
    }
    public static Result Success(String message, Object data){
        return new Result(200,message,data);
    }
    public static Result Success(Object data){
        return new Result(200,"success",data);
    }
    public static Result Success(Integer code,String message){return new Result(code,message,null);};
    public static Result Success(){
        return new Result(200,"success",null);
    }
    public static Result Success(String message){
        return new Result(200,message,null);
    }
    public static Result Success(Integer code, Object data){return new Result(code,"success",data);}

    public static Result fail(Integer code, String message){
        return new Result(code,message,null);
    }

    public static Result fail(String message){
        return new Result(200,message,null);
    }
    public static Result fail(Integer code){
        return new Result(code,"fail",null);
    }
    public static Result fail(){
        return new Result(200,"fail",null);
    }
}
