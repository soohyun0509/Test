
alert("1")
// 제품 등록
function productbtn(){
    alert("2")
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
        url : "/productup",
        type : "get",
        success : re => {
            let html = '<tr>'
                + '<th> 매장번호 </th> <th> 제품명 </th> <th> 가격 </th>'
                + '</tr>';

            re.forEach( list =>{
                html += '<tr>'
                    + '<td>'+ +'</td> <td></td> <td> 수량넣을부분 </td>'
                    + '</tr>'
            })
            document.querySelector('.saleslist').innerHTML = html;
        }

    })
}