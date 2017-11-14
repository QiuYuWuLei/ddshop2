<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar">
    <div id="paramToolbar">
        <button type="button" class="easyui-linkbutton" onclick="addParam()" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" class="easyui-linkbutton" onclick="deleteParam()" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button type="button" class="easyui-linkbutton" onclick="editParam()" data-options="iconCls:'icon-edit',plain:true">编辑</button>
    </div>
</div>
<table id="dgParamList"></table>
<script>
    //新增分组
    function addParam() {
        addTab('新增商品规格模板','item-param-add');
    }
    $('#dgParamList').datagrid({
        title:'商品规格模板列表',
        //将工具栏添加到数据表格中
        toolbar:'#paramToolbar',

        url: 'itemParams',
        //隔行变色，斑马线效果
        striped:true,
        //使列自适应
        fitColumns:true,
        //添加分页工具栏
        pagination:true,
        //每行的前面显示行号
        rownumbers:true,
        //使得数据表格自适应填充父容器
        fit: true,
        //默认显示10条，这样的话就显示20条
        pageSize: 20,
        //分页列表
        pageList: [10,20,50,100],
        columns:[[
            {field:'ck', checkbox: true},
            {field:'id',title:'ID', sortable: true},
            {field:'itemCatName',title:'商品类目',},
            {field:'paramData',title:'规格(只显示分组名称)',formatter:function (value,row,index) {
                //console.log(typeof (value));
                var obj=JSON.parse(value);
                var arr= [];
                $.each(obj,function (i,e) {
                    //i是索引，从0开始。e 是遍历的对象
                    arr.push(e.group);
                });
                return arr;
            }},
            {field:'created',title:'创建日期', formatter:function(value,row,index){
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }},
            {field:'updated',title:'修改时间',width:100,formatter:function (value,row,index) {
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }}
        ]]
    });


    /* function searchForm() {
         $("#dg").datagrid('load',{
             cat:$("#cat").val(),
             params:$("#params").val()
         });
     }*/
</script>