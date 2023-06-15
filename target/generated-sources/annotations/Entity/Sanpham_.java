package Entity;

import Entity.Loaihang;
import Entity.Nhomhang;
import Entity.PicSp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-12-12T21:20:17")
@StaticMetamodel(Sanpham.class)
public class Sanpham_ { 

    public static volatile SingularAttribute<Sanpham, String> tenSanPham;
    public static volatile SingularAttribute<Sanpham, Double> giaNhap;
    public static volatile CollectionAttribute<Sanpham, PicSp> picSpCollection;
    public static volatile SingularAttribute<Sanpham, Double> giaBan;
    public static volatile SingularAttribute<Sanpham, Date> hsd;
    public static volatile SingularAttribute<Sanpham, Double> giamGia;
    public static volatile SingularAttribute<Sanpham, Loaihang> idLoaiHoang;
    public static volatile SingularAttribute<Sanpham, String> moTa;
    public static volatile SingularAttribute<Sanpham, Integer> idSanPham;
    public static volatile SingularAttribute<Sanpham, Integer> soLuong;
    public static volatile SingularAttribute<Sanpham, Nhomhang> idNhomHang;

}