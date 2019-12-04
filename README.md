### News API 활용입니다

+ 실시간 국내 뉴스정보입니다.
+ 제일 최근 기사는 이미지와 타이틀, 언론사 이름을 넣었고 그 외 기사들은 제목과 언론사만 노출했습니다.
+ REST API / JSON

+ 참고
  + [News API](https://newsapi.org/)

+ 요청
https://newsapi.org/v2/top-headlines?country=kr&apiKey=앱키

+ 결과
``` json
{"source":
   {
    "id":null, 
    "name":"Hani.co.kr"
   },
  "author":null,
  "title":"트럼프 “주한미군 주둔하려면 방위비 더 공정하게 부담해야” - 한겨레",  
  "description":"주한미군 주둔 질문에 “어느 쪽으로든 갈 수 있다” “한국은 부자나라…상당히 더 내는 게 공정해”  나토·일본 등에도 무차별 방위비 인상 압박 정은보 협상대사 “추가적 상황 변화 아니야”",
  "url":"http://www.hani.co.kr/arti/international/international_general/919513.html",
  "urlToImage":"http://flexible.img.hani.co.kr/flexible/normal/580/386/imgdb/original/2019/1204/20191204500126.jpg",
  "publishedAt":"2019-12-03T22:26:41Z",
  "content":null
}
```

+ 사용 라이브러리
+ JSON
  + [Volley](https://developer.android.com/training/volley)
+ 이미지 
  + [Fresco](https://github.com/facebook/fresco)
+ RecyclerView
  + [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
