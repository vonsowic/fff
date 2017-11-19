package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.User;
import lombok.Data;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
@Data
public class BasicUser implements User<Integer> {

    private Integer id;

    public BasicUser(){}
    public BasicUser(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id.toString();
    }
}
