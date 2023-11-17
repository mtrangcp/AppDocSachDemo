package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

public class BinhLuan {
    private String  _id;
    private int __v;
    private  String idUser, idSach, date, noiDung;

    public BinhLuan(String _id, int __v, String idUser, String idSach, String date, String noiDung) {
        this._id = _id;
        this.__v = __v;
        this.idUser = idUser;
        this.idSach = idSach;
        this.date = date;
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return "BinhLuan{" +
                "_id='" + _id + '\'' +
                ", __v=" + __v +
                ", idUser='" + idUser + '\'' +
                ", idSach='" + idSach + '\'' +
                ", date='" + date + '\'' +
                ", noiDung='" + noiDung + '\'' +
                '}';
    }

    public BinhLuan() {
    }

    public String get_id() {
        return _id;
    }

    public int get__v() {
        return __v;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
