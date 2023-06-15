/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author letua
 */
@Entity
@Table(name = "pic_sp")
@NamedQueries({
    @NamedQuery(name = "PicSp.findAll", query = "SELECT p FROM PicSp p"),
    @NamedQuery(name = "PicSp.findByIdPc", query = "SELECT p FROM PicSp p WHERE p.idPc = :idPc")})
public class PicSp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pc")
    private Integer idPc;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "picture")
    private String picture;
    @JoinColumn(name = "id_sp", referencedColumnName = "idSanPham")
    @ManyToOne(optional = false)
    private Sanpham idSp;

    public PicSp() {
    }

    public PicSp(Integer idPc) {
        this.idPc = idPc;
    }

    public PicSp(Integer idPc, String picture) {
        this.idPc = idPc;
        this.picture = picture;
    }
    
    public PicSp(String picture, Sanpham idSp) {
        this.picture = picture;
        this.idSp = idSp;
    }

    public Integer getIdPc() {
        return idPc;
    }

    public void setIdPc(Integer idPc) {
        this.idPc = idPc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Sanpham getIdSp() {
        return idSp;
    }

    public void setIdSp(Sanpham idSp) {
        this.idSp = idSp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPc != null ? idPc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PicSp)) {
            return false;
        }
        PicSp other = (PicSp) object;
        if ((this.idPc == null && other.idPc != null) || (this.idPc != null && !this.idPc.equals(other.idPc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PicSp[ idPc=" + idPc + " ]";
    }
    
}
