package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.Arrays;

public class ArrayUser {
    User[] data;

    public ArrayUser(User[] data) {
        this.data = data;
    }

    public User[] getData() {
        return data;
    }

    public void setData(User[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArrayUser{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
