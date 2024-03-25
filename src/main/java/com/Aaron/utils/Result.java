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
    private String token;
    private Object data;


    public static Result Success(Integer code, String message,String token ,Object data){
        return new Result (code,message,token,data);
    }
    public static Result Success(String message,String token, Object data){
        return new Result(200,message,token,data);
    }

    public static Result Success(String message,String token){
        return new Result(200,message,token,null);
    }

    public static Result Success(String token,Object data){
        return new Result(200,"success",token,data);
    }
    public static Result Success(Integer code,String message,String token){return new Result(code,message,token,null);}
    public static Result Success(String token){
        return new Result(200,"success",token,null);
    }

    public static Result Success(Integer code,String token ,Object data){return new Result(code,"success",token,data);}

    public static Result fail(Integer code, String message,String token){
        return new Result(code,message,token,null);
    }

    public static Result fail(String message){
        return new Result(200,message,null,null);
    }
    public static Result fail(Integer code){
        return new Result(code,"fail",null,null);
    }
    public static Result fail(){
        return new Result(200,"fail",null,null);
    }
}
