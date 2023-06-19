package vn.name.appbanhang.utils;

import java.util.ArrayList;
import java.util.List;

import vn.name.appbanhang.model.GioHang;
import vn.name.appbanhang.model.User;

public class Utils {
    public static final String BASE_URL="https://catha-vn.000webhostapp.com/catha/";
//    https://catha-vn.000webhostapp.com/catha/
//    http://192.168.1.3/banhang/
     public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();

    public static String ID_RECEIVED;
    public static final String SENDID = "idsend";
    public static final String RECEIVEDID = "idreceived";
    public static final String MESS = "message";
    public static final String DATETIME = "datetime";
    public static final String PATH_CHAT = "chat";

}
