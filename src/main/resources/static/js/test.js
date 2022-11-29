
// 제품 등록
function productbtn(){
    let info = {
        pname : document.querySelector('.pname').value,
        pprice : document.querySelector('.pprice').value,
        mno : document.querySelector('.mno').value
    }

    $.ajax({
        url : "/productup",
        type : "post",
        data : JSON.stringify(info),
        contentType : "application/json",
        success : re => {
            alert(re);
        }
    })
}


// 매장 등록
function setmarket(){
    let info = {
        mname : document.querySelector('.mname').value
    }
    $.ajax({
        url : "/setmarket",
        type : "post",
        data : JSON.stringify( info ),
        contentType: "application/json",
        success : re => {
            alert(re);
        }
    })

}

// 판매 내역 출력
saleslist()
function saleslist(){

    $.ajax({
        url : "/productlist",
        type : "get",
        success : re => {
            let html = '<tr>'
                + '<th> 매장명 </th> <th> 제품명 </th> <th> 가격 </th>'
                + '</tr>';

            re.forEach( list =>{
                html += '<tr>'
                    + '<td>'+list.mname+'</td> <td>'+list.pname+'</td> <td>'+list.pprice+'</td>'
                    + '</tr>'
            })
            document.querySelector('.saleslist').innerHTML = html;
        }

    })
}