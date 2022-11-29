
let mno=1; // 제품등록-- 기본 1
let mno2=1; // 매장별 제품 출력
let page=1; // 현재 페이지 번호

// 제품 등록
function productbtn(){
    let info = {
        pname : document.querySelector('.pname').value,
        pprice : document.querySelector('.pprice').value,
        mno : mno
    }

    $.ajax({
        url : "/productup",
        type : "post",
        data : JSON.stringify(info),
        contentType : "application/json",
        success : re => {
           location.reload();
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
            location.reload();
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
    saleslist(mno2, page)
}

// 판매 내역 출력
saleslist(mno2, page)
function saleslist( mno2, page ){
    console.log("----")
    console.log(page)
    $.ajax({
        url : "/productlist",
        type : "get",
        data : { "mno" : mno2, "page" : page },
        success : re => {
            console.log(re)
            let list = re.list;
            console.log(list)
            let html = '<tr>'
                + '<th> 매장명 </th> <th> 제품명 </th> <th> 가격 </th>'
                + '</tr>';

            list.forEach( b =>{
                html += '<tr>'
                    + '<td>'+b.mname+'</td> <td>'+b.pname+'</td> <td>'+b.pprice+'</td>'
                    + '</tr>'
            })
            document.querySelector('.saleslist').innerHTML = html;

            // 페이징 출력
            let pagehtml = '';
            // 이전 페이지
            if( page <= 1 ){
                pagehtml += '<button onclick="saleslist('+(mno2+','+page)+')"> 이전 </button>';
            }else{
                pagehtml += '<button onclick="saleslist('+mno2+','+(page-1)+')"> 이전 </button>';
            }
            // console.log("page" )
            // console.log(page)
            // console.log("mno2")
            // console.log(mno2)

            // [ 페이지번호 버튼 ]
            for( let page = re.startbtn; page <= re.endbtn; page++ ){
                pagehtml += '<button type="button" onclick="saleslist('+ mno2 +','+ page+')">'+ page +'</button>'
            } console.log("page :  " + page)

            // 다음 페이지
            if( page >= re.totalpage ){
                pagehtml += '<button onclick="saleslist('+(mno2+','+page)+')"> 다음 </button>';
            }
            else{
                pagehtml += '<button onclick="saleslist('+mno2+','+(page+1)+')"> 다음 </button>';
            }
            document.querySelector('.pagebox').innerHTML = pagehtml;
        }
    })
}

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