<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<table id="dg"></table>
<script>
    var toolbar=[{
        iconCls:'icon-add',
        text:'新增',
        handler:function () {

        }
    },{
        iconCls:'icon-remove',
        text:'删除',
        handler:function () {
            doDelete();
        }
    },{
        iconCls:'icon-edit',
        text:'编辑',
        handler:function () {

        }
    },{
        iconCls:'icon-up',
        text:'上架',
        handler:function () {
            doUp();
        }
    },{
        iconCls:'icon-down',
        text:'下架',
        handler:function () {
            doDown();
        }
    }];
    $('#dg').datagrid({
        //将工具栏添加到数据表格中
        toolbar: toolbar,

        url: 'items',
        //隔行变色，斑马线效果
        striped:true,
        //支持多列排序
        multiSort:true,
        //添加分页工具栏
        pagination:true,
        //每行的前面显示行号
        rownumbers: true,
        //使得数据表格自适应填充父容器
        fit: true,
        //默认显示10条，这样的话就显示20条
        pageSize: 20,
        //分页列表
        pageList: [10,20,50,100],
        columns:[[
            {field: 'ck', checkbox: true},
            {field:'id',title:'商品编号',width:100,sortable:true},
            {field:'title',title:'商品名称',width:500,sortable:true},
            {field:'status',title:'状态',width:100,formatter:function (value,row,index) {
                switch (value){
                    case 1:
                                return "正常";
                                break;
                    case 2:
                                return "下架";
                                 break;
                    case 3:
                                return "删除";
                                break;
                    default:
                                return "未知";
                                break;
                }
            }},
            {field:'catName',title:'分类名称',width:80},
            {field:'priceView',title:'价格',width:100},
            {field:'created',title:'创建时间',width:100,formatter:function (value,row,index) {
                return moment(value).format('LL');
            }},
            {field:'updated',title:'修改时间',width:100,formatter:function (value,row,index) {
                return moment(value).format('LL');
            }}
        ]]
    });


    //删除数据
    function doDelete() {
        //把你选中的 数据查询出来。
        var selectRows = $('#dg').datagrid("getSelections");
        if (selectRows.length < 1) {
            $.messager.alert("提示消息", "请选中要删的商品!");
            return;
        }

        //真删除数据
        //提醒用户是否是真的删除数据
        $.messager.confirm("确认消息", "您确定要删除该商品吗？", function (r) {
            if (r) {
                //真删除了  1,3,4
                var ids = [];
                for (var i = 0; i < selectRows.length; i++) {
                    ids.push( selectRows[i].id);
                }

                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/batch',
                    //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                    { 'ids[]': ids },
                    //callback:后台处理成功的回调函数，相当于success，function类型
                    function (data) {
                        if (data >0) {
                            //刷新表格，去掉选中状态的 那些行。
                            $('#dg').datagrid("reload");
                            //去掉选中的框
                            $('#dg').datagrid("clearSelections");
                            $.messager.alert("提示消息","删除成功~~");
                        } else {
                            $.messager.alert("删除失败~~", data);
                        }
                    },
                    //dataType:返回的数据类型，如：json，String类型
                    'json'
                    );

            }
        });

    }



    //上架
    function  doUp() {
        //把你选中的 数据查询出来。
        var selectRows=$("#dg").datagrid("getSelections");
        if (selectRows.length<1){
            $.messager.alert("提示消息", "请选中要上架的数据!");
            return;
        }

        //真上架商品
        //提醒用户是否是真的上架商品
       $.messager.confirm("确认消息", "您确定要上架该商品吗？",function (r) {
            if (r){
                //确认删除，获得选中行的id
                var ids=[];
                for (var i=0;i<selectRows.length;i++){
                    ids.push(selectRows[i].id);
                }

                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/batchUp',
                    {"ids[]":ids},
                    function (data) {
                        if(data>0){
                            //刷新表格，去掉选中状态的 那些行。
                            $('#dg').datagrid("reload");
                            //去掉选中的框
                            $('#dg').datagrid("clearSelections");
                            $.messager.alert("提示消息","上架成功~~");
                        }else {
                           $.messager.alert("上架失败~~", data);
                        }
                    },
                    'json'
                );
            }
        });

    }


    //下架
    function  doDown() {
        //把你选中的 数据查询出来。
        var selectRows=$("#dg").datagrid("getSelections");
        if (selectRows.length<1){
            $.messager.alert("提示消息", "请选中要下架的数据!");
            return;
        }

        //真下架商品
        //提醒用户是否是真的下架商品
        $.messager.confirm("确认消息", "您确定要下架该商品吗？",function (r) {
            if (r){
                //确认下架，获得选中行的id
                var ids=[];
                for (var i=0;i<selectRows.length;i++){
                    ids.push(selectRows[i].id);
                }

                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/batchDown',
                    {"ids[]":ids},
                    function (data) {
                        if(data>0){
                            //刷新表格，去掉选中状态的 那些行。
                            $('#dg').datagrid("reload");
                            //去掉选中的框
                            $('#dg').datagrid("clearSelections");
                            $.messager.alert("提示消息","下架成功~~");
                        }else {
                            $.messager.alert("下架失败~~", data);
                        }
                    },
                    'json'
                );
            }
        });

    }


</script>
