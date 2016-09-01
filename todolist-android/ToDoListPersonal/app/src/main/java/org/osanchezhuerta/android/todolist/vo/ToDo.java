package org.osanchezhuerta.android.todolist.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by sanchezocth on 30/08/2016.
 */
public class ToDo implements Parcelable {

    private int id;
    private String category;
    private String summary;
    private String description;

    @Override
    public int describeContents() {
        return 0;
    }

    public ToDo() {
        super();
    }

    private ToDo(Parcel in) {
        super();
        this.id = in.readInt();
        this.category = in.readString();
        this.summary = in.readString();
        this.description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.getId());
        parcel.writeString(this.getCategory());
        parcel.writeString(this.getSummary());
        parcel.writeString(this.getDescription());
    }

    public static final Parcelable.Creator<ToDo> CREATOR = new Parcelable.Creator<ToDo>() {
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDo toDo = (ToDo) o;

        if (getId() != toDo.getId()) return false;
        if (!getCategory().equals(toDo.getCategory())) return false;
        if (!getSummary().equals(toDo.getSummary())) return false;
        return getDescription().equals(toDo.getDescription());

    }
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getSummary().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
