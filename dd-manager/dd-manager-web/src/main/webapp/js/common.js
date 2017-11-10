
var ddshop = {
    //点击左侧导航树上的节点，添加选项卡
    registerMenuEvent:function(){
       // var this_=this;
        var $tree=$("#menu .easyui-tree");
        //console.log($tree);
        $tree.tree({
            onClick: function(node){
                addTab(node.text,node.attributes.href);
            }
        });
    }
}

function addTab(text,href) {
    //判断选项卡是否重复打开
    if ($('#tab').tabs('exists', text)) {
        $('#tab').tabs('select', text);//选中并刷新
    } else {
        $("#tab").tabs("add", {
            title: text,
            href: href,
            closable: true
        });
    }
}


