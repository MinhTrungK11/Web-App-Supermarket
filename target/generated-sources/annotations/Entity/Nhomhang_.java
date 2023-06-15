package Entity;

import Entity.Loaihang;
import Entity.Sanpham;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-12-12T21:20:17")
@StaticMetamodel(Nhomhang.class)
public class Nhomhang_ { 

    public static volatile SingularAttribute<Nhomhang, String> tenNhomHang;
    public static volatile CollectionAttribute<Nhomhang, Sanpham> sanphamCollection;
    public static volatile CollectionAttribute<Nhomhang, Loaihang> loaihangCollection;
    public static volatile SingularAttribute<Nhomhang, String> idNhomHang;

}