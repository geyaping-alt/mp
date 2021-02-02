var table = layui.table;
//第一个实例
var tableIns =  table.render({
    elem: '#roleList'
    ,url: '/role/list' //数据接口
    ,page: true //开启分页
    ,parseData: function (res) {
        return{
            "code": res.code,
            "msg": res.msg,
            "count": res.data.count,
            "data": res.data.records
        };
    }
    ,cols: [[ //表头
        {field: 'roleName', title: '真实姓名'}
        ,{field: 'createTime', title: '创建时间'}
        ,{title: '操作', toolbar:'#barDemo'}
    ]]
});

/**
 * 查询条件
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            roleName: $("#roleName").val()
        }
        ,page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

    let roleId = data.roleId;

    if(layEvent === 'detail'){ //查看

        openLayer('/role/toDetail/'+roleId,"角色详情");

        showTree('/role/listResource'+roleId+'/1','resource',false);

    } else if(layEvent === 'del'){ //删除
        layer.confirm('真的删除吗', function(index){
            layer.close(index);
            myDelete('/role/'+roleId);
        });
    } else if(layEvent === 'edit'){ //编辑
        //do something
        openLayer('/role/toUpdate/'+roleId,"修改角色");

        showTree('/role/listResource'+roleId+'/0','resource');

        mySubmit('updateSubmit','PUT',addIds);
    }
});

/**
 * 进入新增页面
 */
function intoAdd() {

    openLayer('/role/toAdd',"新增角色");

    showTree('/role/listResource','resource');

    mySubmit('addSubmit','POST',addIds);
}

/**
 * 通用的资源树方法
 * @param url
 * @param id
 */
function  showTree(url,id, showCheckbox) {

    if(typeof (showCheckbox) == 'undefined'){
        showCheckbox = true;
    }

    $.ajax({
        url: url
        ,async: false
        ,type: 'GET'
        ,success:function (res) {
            if(res.code == 0){
                layui.tree.render({
                    elem: '#'+id
                    ,data: res.data
                    ,id: id
                    ,showCheckbox: showCheckbox
                });
            }
        }
    });
}

var addIds = function (field) {
    let checkedData = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checkedData,[]);
}

function getIds(checkedData,arr) {
    for(let i in checkedData){
        arr.push(checkedData[i].id);
        arr = getIds(checkedData[i].children,arr);
    }
    return arr;
}