
let mno=1; // 제품등록-- 기본 1
let mno2=1; // 매장별 제품 출력

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
getmarket()
// 매장리스트 출력 - 제품등록할때 써먹을 버튼
function getmarket(){
    $.ajax({
        url:"/getmarket",
        type:"get",
        success : re=>{
            let html=""
            re.forEach(list=>{
                html+='<button onclick="changemno('+list.mno+')">'+list.mname+'</button>'
            })
            document.querySelector(".mnamelist").innerHTML=html;
        }

    })

}
getmarket2()
// 매장리스트 출력 - 매장별 판매상태 볼때 사용할 버튼
function getmarket2(){
    $.ajax({
        url:"/getmarket",
        type:"get",
        success : re=>{
            let html=""
            re.forEach(list=>{
                html+='<button onclick="changemno2('+list.mno+')">'+list.mname+'</button>'
            })
            document.querySelector(".mnamelist2").innerHTML=html;
        }

    })

}
// 매장 번호 변경
function changemno(changemno){
    mno=changemno;   alert(mno + " 매장 선택")
}
// 매장 번호 변경
function changemno2(changemno){

    mno2=changemno;
    saleslist()
}

// 판매 내역 출력
saleslist()
function saleslist(){

    $.ajax({
        url : "/productlist",
        type : "get",
        data : {"mno" : mno2},
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

printtotal()
// 매장별 매출 합계 출력
function printtotal(){
    $.ajax({
        url : "/printtotal",
        data : {"mno" : mno2 , "cdate" : document.querySelector(".cdate").value},
        type : "get",
        success: re=>{
            document.querySelector(".daytotal").innerHTML=re+" 원"
        }
    })
}