<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div style="padding:5px;" >
    <a id="import" class="easyui-linkbutton" onclick="importItems()">一键导入商品数据到索引库</a>
    <span id="msg"></span>
</div>
<script type="text/javascript">
    function importItems() {
        //点击完之后不可再次点击，除非更新
        $('#import').linkbutton('disable');
        //往span中添加信息
        $('#msg').html('导入中，请稍后...');
        $.post(
            //url:提交到哈欧泰的哪里
            'search/item/import',
            //data：没有传递参数
            null,
            //success:回调函数
            function (data) {
                if(data.success){
                    $.messager.alert('提示', data.message);
                } else {
                    $.messager.alert('提示','导入索引库失败！');
                }
                $('#msg').html('');
            }
        );
    }

</script>