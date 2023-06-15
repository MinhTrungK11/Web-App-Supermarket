/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class CookieUtils {
    
    public static String get(String name, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equalsIgnoreCase(name)){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
    
    public static Cookie add(String name, String value, int hours, HttpServletResponse reponse){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60*60*hours);
        cookie.setPath("/");
        reponse.addCookie(cookie);
        
        return cookie;
    }
}
