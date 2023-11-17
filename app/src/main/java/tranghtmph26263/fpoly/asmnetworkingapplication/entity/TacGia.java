package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.ArrayList;

public class TacGia {
    private int __v, namSinh;
    private String ten, _id;
    private ArrayList<String> sachs;

    public TacGia(int __v, int namSinh, String ten, String _id, ArrayList<String> sachs) {
        this.__v = __v;
        this.namSinh = namSinh;
        this.ten = ten;
        this._id = _id;
        this.sachs = sachs;
    }

    @Override
    public String toString() {
        return "TacGia{" +
                "__v=" + __v +
                ", namSinh=" + namSinh +
                ", ten='" + ten + '\'' +
                ", _id='" + _id + '\'' +
                ", sachs=" + sachs +
                '}';
    }

    public TacGia() {
    }

    public int get__v() {
        return __v;
    }

    public String get_id() {
        return _id;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public ArrayList<String> getSachs() {
        return sachs;
    }

    public void setSachs(ArrayList<String> sachs) {
        this.sachs = sachs;
    }
}
