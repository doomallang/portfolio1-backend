package com.doomole.portfolio.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Semaphore;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public void bfs(int[][] maps, int[][] visited){
        int x = 0;
        int y = 0;
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];
            for(int i = 0; i < 4; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1)
                    continue;
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }
        }
    }

    public int solution(int[][] n) {
        int W = n[0][0];
        int V = n[0][1];

        int[][] newArray = Arrays.copyOfRange(n, 1, n.length);

        Arrays.sort(newArray, (a, b) -> {
            if (a[0] != b[0]) {
                // [0] 값을 기준으로 오름차순 정렬
                return Integer.compare(a[0], b[0]);
            } else {
                // [0] 값이 같으면 [1] 값을 기준으로 내림차순 정렬
                return Integer.compare(b[1], a[1]);
            }
        });

        int maxValue = 0;
        for(int i = 0; i < newArray.length; i++){
            int count = 0;
            int weight = 0;
            int value = 0;
            if(newArray[i][0] > V){
                continue;
            }
            count++;
            weight += newArray[i][0];
            value += newArray[i][1];
            for(int j = i+1; j < newArray.length; j++){
                if(count == W) {
                    break;
                }
                if(newArray[j][0] > V){
                    break;
                }
                if(weight + newArray[j][0] > V) {
                    break;
                }
                count++;
                weight += newArray[j][0];
                value += newArray[j][1];
            }
            if(value > maxValue){
                maxValue = value;
            }
        }

        return maxValue;
    }

    @GetMapping("/temp")
    public void test1() {
        int[][] n = {{4, 7}, {6, 13}, {4, 8}, {3, 6}, {5, 12}};

        int answer = solution(n);
        System.out.println(answer);
    }

    @GetMapping("/semaphore")
    public void test2() {
        Runnable runnable = new Runnable() {
            final Random random = new Random();
            final Semaphore available = new Semaphore(3);
            int count = 0;

            @Override
            public void run() {
                int time = random.nextInt(15);
                int num = count++;

                try {
                    available.acquire();
                    System.out.println("Executing long-running action for " + time + " second... #" + num);

                    Thread.sleep(time * 1000);

                    System.out.println("Done with #" + num + "!");
                    available.release();
                } catch(InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        };

        for(int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    @GetMapping("/mms")
    public void sendSms() {
        String sendPhone = "080-869-4273";
        String destPhone = "01024873788";
        String msgBody = "안녕하세요.";

        String mmsUrl = "http://222.231.12.68:8887";
        String vasId = "MMS_PASS_STOCK";
        String vasPid = "MMS_PASS_STOCK";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String transactionId = sdf.format(Calendar.getInstance().getTime());
        String contentID = "ABCDEFGHIJKLMNOP";

        String boundary = "NextPart_016-772-8798";
        String contentPartsBoundary = "ContentParts_016-772-8798";

        // XML 메시지 및 multipart 본문 구성
        String xmlEnvelope =
                "--" + boundary + "\r\n" +
                        "Content-Type: text/xml; charset=\"ks_c_5601-1987\"\r\n" +
                        "Content-ID: </tnn-200102/mm7-submit>\r\n\r\n" +
                        "<?xml version=\"1.0\" encoding=\"euc-kr\"?>\r\n" +
                        "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
                        "   <env:Header>\r\n" +
                        "       <mm7:TransactionID xmlns:mm7=\"http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-2\" env:mustUnderstand=\"1\">\r\n" +
                        "           23088\r\n" +
                        "       </mm7:TransactionID>\r\n" +
                        "   </env:Header>\r\n" +
                        "   <env:Body>\r\n" +
                        "       <mm7:SubmitReq xmlns:mm7=\"http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-2\">\r\n" +
                        "           <MM7Version>5.3.0</MM7Version>\r\n" +
                        "           <SenderIdentification>\r\n" +
                        "               <VASPID>testVASPID</VASPID><VASID>testVASID</VASID>\r\n" +
                        "               <SenderAddress>0117340299</SenderAddress>\r\n" +
                        "               <CallBack>114</CallBack>\r\n" +
                        "           </SenderIdentification>\r\n" +
                        "           <Recipients><To><Number>0117340299</Number></To></Recipients>\r\n" +
                        "           <ServiceCode>0000</ServiceCode><LinkedID>mms00016666</LinkedID>\r\n" +
                        "           <MessageClass>137</MessageClass>\r\n" +
                        "           <TimeStamp>2004-01-19T14:29:59Z</TimeStamp>\r\n" +
                        "           <EarliestDeliveryTime>2004-01-19T14:29:59Z</EarliestDeliveryTime>\r\n" +
                        "           <DeliveryReport>False</DeliveryReport>\r\n" +
                        "           <ReadReply>False</ReadReply>\r\n" +
                        "           <ChargedParty>Sender</ChargedParty>\r\n" +
                        "           <DistributionIndicator>0000</DistributionIndicator>\r\n" +
                        "           <KisaOrigCode>123456789</KisaOrigCode>\r\n" +
                        "           <Subject>엽기소녀</Subject>\r\n" +
                        "           <Content href=\"cid:SaturnPics-01020930@news.tnn.com\" allowAdaptations=\"True\"/>\r\n" +
                        "       </mm7:SubmitReq>\r\n" +
                        "   </env:Body>\r\n" +
                        "</env:Envelope>\r\n" +
                        "--" + contentPartsBoundary + "\r\n" +
                        "Content-Type: text/plain; charset=\"euc-kr\"\r\n" +
                        "Content-Transfer-Encoding: base64\r\n" +
                        "X-Kmms-SVCCODE: 80010350100\r\n" +
                        "rnskadl=\r\n" +
                        "--" + contentPartsBoundary + "--\r\n" +
                        "--" + boundary + "--\r\n";


        System.out.println(xmlEnvelope);

        RestClient restClient = RestClient.create();
        try {
            String response = restClient.post()
                    .uri(mmsUrl)
                    .header(HttpHeaders.CONTENT_TYPE, "multipart/related;boundary=\"" + boundary + "\"; type=\"text/xml\"; start=\"</tnn-200102/mm7-submit>\"")
                    .accept(MediaType.ALL)
                    .header("SOAPAction", "")
                    .body(xmlEnvelope)
                    .retrieve()
                    .body(String.class);

            System.out.println(response);
        } catch(Exception e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }
}
