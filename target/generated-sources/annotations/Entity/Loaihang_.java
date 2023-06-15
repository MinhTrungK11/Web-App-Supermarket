package Entity;

import Entity.Nhomhang;
import Entity.Sanpham;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-12-12T21:20:17")
@StaticMetamodel(Loaihang.class)
public class Loaihang_ { 

    public static volatile SingularAttribute<Loaihang, String> tenLoaiHang;
    public static volatile CollectionAttribute<Loaihang, Sanpham> sanphamCollection;
    public static volatile SingularAttribute<Loaihang, String> idLoaiHang;
    public static volatile SingularAttribute<Loaihang, Nhomhang> idNhomHang;

}