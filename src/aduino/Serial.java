package aduino;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Serial {

    private void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            //클래스 이름을 식별자로 사용하여 포트 오픈
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                //포트 설정(통신속도 설정. 기본 9600으로 사용)
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                //Input,OutputStream 버퍼 생성 후 오픈
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                //읽기, 쓰기 쓰레드 작동
                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /**
     *
     */
    //데이터 수신
    public static class SerialReader implements Runnable {
        InputStream in;

        SerialReader(InputStream in) {
            this.in = in;
        }

        /**
         * 아두이노에서 임의의 값을 (text)로 통신에 보내면, 해당 통신값을 받아,
         * 프로그램을 실행하도록 구현.
         */
        public void run() {
            byte[] buffer = new byte[1024];
            int len = -1;
            try {
                while ((len = this.in.read(buffer)) > -1) {
                    String temp = new String(buffer, 0, len);
                    switch (temp) {
                        case "a": {
                            Process oProcess = new ProcessBuilder("notepad.exe").start();
                            break;
                        }
                        case "b": {
                            Process oProcess = new ProcessBuilder("calc.exe").start();
                            break;
                        }
                        case "c": {
                            Process oProcess = new ProcessBuilder("control").start();
                            break;
                        }
                        case "d": {
                            Process oProcess = new ProcessBuilder("D:\\workset\\eclipse-jee-photon-R-win32-x86_64\\eclipse\\eclipse.exe").start();
                            break;
                        }
                        case "e": {
                            Process oProcess = new ProcessBuilder("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2018.2.3\\bin\\idea64.exe").start();
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    //데이터 송신
    public static class SerialWriter implements Runnable {
        OutputStream out;

        SerialWriter(OutputStream out) {
            this.out = out;
        }

        public void run() {
            try {
                int c = 0;
                while ((c = System.in.read()) > -1) {
                    this.out.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            (new Serial()).connect("COM4"); //입력한 포트로 연결
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}