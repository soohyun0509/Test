
alert("1")
// 제품 등록
function productbtn(){
    alert("2")
    info = {
        pname : document.querySelector('.pname').value,
        pamount : document.querySelector('.pamount').value
    }

    $.ajax({
        url : "",
        type : "post",
        data : JSON.stringify(info),
        contentType : "application/json",
        success : re => {
            alert(re);
        }
    })
}


// 매장 등록
function marketbtn(){
    let mname = document.querySelector('.mname').value;

    $.ajax({
        url : "",
        type : "post",
        data : JSON.stringify(mname),
        contentType : "application/json",
        success : re => {
            alert(re);
        }
    })

}

// 판매 내역 출력
saleslist()
function saleslist(){

    $.ajax({
        url : "",
        type : "get",
        success : re => {
            let html = '<tr>'
                    + '<th> 매장명 </th> <th> 제품명 </th> <th> 수량 </th>'
                    + '</tr>';

        /* 반복문 돌아갈 자리 */
                html += '<tr>'
                    + '<td> 매장이름넣을부분 </td> <td> 제품명넣을부분 </td> <td> 수량넣을부분 </td>'
                    + '</tr>'
        document.querySelector('.saleslist').innerHTML = html;
        }

    })
}