# Hobbies (Toy)

```
1. Hobbies Coding
```

개인적인 취미로 단순 처리작업들을 편하게 하기 위한 작업파일들의 묶음.  

ImageRenameFile.java
---
관리하는 폴더에서 의미없는 파일을 삭제하고, 인터넷에서 받은 파일들이 잘못된 인코딩이 되어있을 경우 디코딩 처리를 하기 위한 class
> ImageRenameFile.java > 빈폴더를 삭제하면서, URLEncoding 된 파일들을 Decoding 처리해준다.

FileInSearch.java
---
특정 파일 안에 특정 검색어를 검색. 단순 검색기능에서, 검색할 경우에 여러 조건들로 무언가 개발할 수 있으므로 abstract 로 분리 처리함.
> FileInSearch.java > abstract 에서 해당 line 을 읽어와 map 에 담음. 분석하기 위해서는 별도의 checkPattern() 메소드를 생성해서 처리

SameCountCheck.java
---
해당 문자 패턴이 얼마만큼 같은지, 판별
> SameCountCheck.java > 해당 문자 패턴이 얼마만큼 같은지 다른지를 판별

URLEncodingRenameFile.java
---
특정 폴더에 있는 파일들을  UrlDecoding 하도록 파일명 변경


FileInSearch.java
---
추상화객체로 만들었기 때문에, 패턴을 처리하기 위해서,

```java
    public void checkPattern(String line) {
        System.out.println(line);
    }

    public static void main(String[] args) {

        final String PATH = "D:\\1234\\";
        final String[] inKeyword = {"rtbWinApi"," 400 "};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        UrlCallTest check = new UrlCallTest();
        int cnt = check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
        System.out.println(cnt);
    }
```
 
FileInSearch 를 상속받은 후에, 필수조건(inKeyword), 선택조건(orKeyword), 제외조건(exceptKeyword)에 해당 값으로 처리됩니다.  
필수조건의 경우에는 AND 조건절처럼 무조건 해당 값이 있어야하며,  
선택조건의 경우에는 둘중에 하나에 대한 조건처리입니다.  
제외조건은 해당 값이 없는 경우를 출력하는 조건입니다.  

checkPattern 은 메소드 구현해야하며, 여러 조건을 처리하기 위한 값으로 해당 패턴이 있을 때, 출력을 시키거나, 별도의 동작을 수행시킬 수 있습니다.  
