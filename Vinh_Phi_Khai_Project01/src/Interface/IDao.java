/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author Quang Phi
 * @param <M> là các class trong Pakage Model
 * @param <O> Là cái kiểu dữ liệu Integer,String
 */
public interface IDao<M, O> {

    List<M> getAll();

    boolean insert(M model);

    boolean update(M model);

    boolean delete(O object);

    List<M> findModel(O object);
}
