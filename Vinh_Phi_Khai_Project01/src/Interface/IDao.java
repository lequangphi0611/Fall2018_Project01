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
 * @param <model>
 * @param <object>
 */
public interface IDao<model,object> {
    List<model> getAll();
    void Delete(object ob);
}
