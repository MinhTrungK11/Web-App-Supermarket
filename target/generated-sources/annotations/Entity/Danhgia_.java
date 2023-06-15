package Entity;

import Entity.Account;
import Entity.Sanpham;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-12-12T21:20:17")
@StaticMetamodel(Danhgia.class)
public class Danhgia_ { 

    public static volatile SingularAttribute<Danhgia, Account> taiKhoan;
    public static volatile SingularAttribute<Danhgia, String> noiDung;
    public static volatile SingularAttribute<Danhgia, Integer> idDanhGia;
    public static volatile SingularAttribute<Danhgia, Date> thoiGian;
    public static volatile SingularAttribute<Danhgia, Sanpham> idSanPham;

}