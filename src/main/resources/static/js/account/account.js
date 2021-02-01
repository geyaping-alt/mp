layui.laydate.render({
    elem: '#createrTimeRange'
    ,range:true
});

var table = layui.table;

var tableIns = table.render({
    id : 'test',
    elem : '#accountList',
    url : "/account/list",
    page : true,
    parseData : function (res) {
        return {
            "code" : res.code,
            "msg" : res.msg,
            "count" : res.data.count,
            "data" : res.data.records
        };
    },
    cols : [[
        {field : 'userName', title : '用户名'},
        {field : 'realName', title : '真实姓名'},
        {field : 'roleName', title : '角色名称'},
        {field : 'sex', title : '性别'},
        {field : 'createTime', title : '创建时间'},
        { title : '', toolbar : '#barDemo'}
    ]]
});

/**
 * 查询
 */
function query() {

    tableIns.reload({
        where: {
            realName: $("#realName").var(),
            email: $("#email").var(),
            createTimeRange: $("#createTimeRange").var()
        },page: {
            curr:1
        }
    });
}

/**
 * 进入新增页面
 */
function intoAdd() {

    openLayer("/account/toAdd","新增账号");

    let form = layui.form;

    form.render();

    mySubmit('addSubmit',"POST");
}

table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

    let accountId = data.accountId;

    if(layEvent === 'detail'){ //查看

        openLayer('/account/toDetail/'+accountId,"账户详情");

    } else if(layEvent === 'del'){ //删除
        layer.confirm('真的删除吗', function(index){
            layer.close(index);
            myDelete('/account/'+accountId);
        });
    } else if(layEvent === 'edit'){ //编辑
        //do something
        openLayer('/account/toUpdate/'+accountId,"修改账户");

        layui.form.render();

        mySubmit('updateSubmit','PUT');
    }
});

layui.form.verify({
    checkUserName: function(value, item) { //value：表单的值、item：表单的DOM对象

        let error=null;
        let url = '/account/' + value;
        let accountId = $("input[name='accountId']").val();
        if(typeof (accountId) != 'undefined'){
            url += '/' + accountId;
        }
        $.ajax({
            url: url
            ,async: false
            ,type: 'GET'
            ,success:function (res) {
                if(res.code == 0){
                    if(res.data > 0){
                        error="用户名已经存在";
                    }
                }else{
                    error="用户名检测出错";
                }
            },error: function () {
                error="用户名检测出错";
            }
        });
        if(error != null){
            return error;
        }
    }
});



