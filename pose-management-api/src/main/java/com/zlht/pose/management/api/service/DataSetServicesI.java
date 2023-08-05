package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.DataSet;

import java.util.Map;

public interface DataSetServicesI {

    /**
     * 查询数据集
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<DataSet> queryDataSetList(int type, int pageNum, int pageSize, String dataSetName);


    /**
     * 创建数据集
     *
     * @param dataSet
     * @return
     */

    Map<String, Object> createDataSet(DataSet dataSet);

    /**
     * 更新数据集
     *
     * @param id
     * @param dataSet
     * @return
     */
    Map<String, Object> updateDataSet(int id, DataSet dataSet);

    /**
     * 删除数据集
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteDataSet(int id);


    /**
     * 判断是否已存在
     *
     * @param authInstitution
     * @return
     */
    boolean checkDataSetExistByNameAndType(DataSet authInstitution);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkDataSetExistById(int id);
}
