package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.ArrayList;

public class Sach2 {
    private String  _id;
    private int __v;
    private String tenSach, moTa, anhBia;
    private int namsx;
    private TacGia tacGia;
    private ArrayList<BinhLuan> binhLuan;

    public Sach2() {
    }

    @Override
    public String toString() {
        return "Sach2{" +
                "_id='" + _id + '\'' +
                ", __v=" + __v +
                ", tenSach='" + tenSach + '\'' +
                ", moTa='" + moTa + '\'' +
                ", anhBia='" + anhBia + '\'' +
                ", namsx=" + namsx +
                ", tacGia=" + tacGia +
                ", binhLuan=" + binhLuan +
                '}';
    }

    public Sach2(String _id, int __v, String tenSach, String moTa, String anhBia, int namsx, TacGia tacGia, ArrayList<BinhLuan> binhLuan) {
        this._id = _id;
        this.__v = __v;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.anhBia = anhBia;
        this.namsx = namsx;
        this.tacGia = tacGia;
        this.binhLuan = binhLuan;
    }

    public String get_id() {
        return _id;
    }

    public int get__v() {
        return __v;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public int getNamsx() {
        return namsx;
    }

    public void setNamsx(int namsx) {
        this.namsx = namsx;
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public ArrayList<BinhLuan> getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(ArrayList<BinhLuan> binhLuan) {
        this.binhLuan = binhLuan;
    }
}
