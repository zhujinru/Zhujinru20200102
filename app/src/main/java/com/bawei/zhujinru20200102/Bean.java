package com.bawei.zhujinru20200102;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Bean {
    @Id
    private int id;
    @Transient
    private String name;
    @Property
    private int age;
    @Generated(hash = 1697194836)
    public Bean(int id, int age) {
        this.id = id;
        this.age = age;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
