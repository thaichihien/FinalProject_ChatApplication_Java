# FinalProject_ChatApplication_Java

- [Công việc](#công-việc)
- [Giải thích package](#giải-thích-package)
- [Lưu ý](#lưu-ý)


## Hướng dẫn test UI
- ***Giao diện người dùng*** : chạy file _Login.java_ hoặc _MainFormUser.java_ trong package **userchatapp**
- ***Giao diện quản trị:*** chạy file _MainFormAdmin.java_ trong package **adminchatapp**
## Giải thích package:
- **adminchatapp** : chứa giao diện quản trị
- **chatservice** : chứa file liên quan đến server client socket
- **database:** chứa file tương tác database
- **datastructure:** chứa các cấu trúc dữ liệu tự định nghĩa
- **resource:** chứa hình ảnh
- **test:** chứa môi trường test Jframe
- **uichatcomponent:** chứa các component phục vụ cho UI
- **userchatapp:** : chứa giao diện người dùng
## Cài đăt thư viện
- **PostgreSQL Driver** : để kết nối csdl Postgres từ Java : https://jdbc.postgresql.org/download/
- **JCalendar:** giao diện Swing để chọn ngày, dùng bên Admin, sử dụng **jcalendar-1.4.jar**: https://toedter.com/jcalendar/
- **Java Mail**: cho phần gửi email với mật khẩu random mới: https://javaee.github.io/javamail/
- **JAF (JavaBeans Activation Framework)** : phục vụ cho chức năng gửi mail: https://www.oracle.com/java/technologies/downloads.html
## Hướng dẫn tạo cơ sở dự liệu Postgres:
- Đọc hướng dẫn chi tiết tại thư mục ***doc***
## Hướng dẫn kết nối Postgres từ java:
- Nhớ add driver nói trên
- Copy đoạn code bên dưới, tạo file tên là **DatabaseConfig.java** đặt trong thư mục **database**, dán code vào
- Sửa tài khoản và mật khẩu cho phù hợp:
```
package database;
public class DatabaseConfig {
    public static String databaseName = "finalproject_chatapplication";
    public static String username = System.getenv("POSTGRES_USERNAME"); // thay bằng chuỗi tài khoản postgres nếu k dùng biến môi trường, mặc định là "postgres"
    public static String password = System.getenv("POSTGRES_PASSWORD"); //thay bằng chuỗi mật khẩu postgres nếu k dùng biến môi trường
}
```
## Cài đặt cho Login,Register và Forgot Password
- Cài hai thư viện: **Java Mail**, **JAF**
- Đối với forgot password: thêm 2 biến môi trường **USER_EMAIL_PROJECT** và **PASSWORD_EMAIL_PROJECT** lần lượt cho email và mật khẩu để gửi mật khẩu random mới cho người dùng
- Tạo file `.secretkey` tại ***src/utils*** với nội dung:

`secret_key = <your secret key>`
## Hướng dẫn test code:
### Đối với chức năng Login và Register:
- Viết code tại các file Login.java Register.java. Chạy code ở các file này
- Code thành công khi vào trang chủ có hiển thị tên tài khoản **(username)** ở góc trên bên trái
### Đối với chức năng ở các Menu Chat,AddFriend,Group
- Copy code tại : https://pastecode.io/s/gmcntshd
- Tạo một file là **MainFrameTest.java**  trong thư mục **test** (k có thì tạo thư mục), dán vào đoạn code vừa copy
- Vào code test chức năng ở /test/MainFrameTest.java
- Tại hàm tạo của MainFrameTest, chuẩn bị giao diện menu mà mình đang làm: (Ví dụ bên dưới đang thực hiện test chức năng cho **MenuAddFriend**)
```
//set menu here
MenuAddFriend menuTest = new MenuAddFriend(testAccount);
setMenu(menuTest);
```
- Viết test các chức năng bên dưới nếu cần
### Đối với chức năng ở các Menu AccountManager,GroupManager,LoginHistory
- Tương tự nhưng không truyền testAccount mà truyền this:
```
//set menu here
MenuAccountManager menuTest = new MenuAccountManager(this);
setMenu(menuTest);
```
## Một số đoạn code mẫu:
- Lấy dữ liệu từ database:
```
DatabaseManagment database = DatabaseManagment.getInstance();
ArrayList<UserAccount> test = database.getAllAccounts();
for(UserAccount acc : test){
    System.out.println(acc.toString());
}
```
- Thực hiện nạp dữ liệu vào bảng:
```
DatabaseManagment database = DatabaseManagment.getInstance();
ArrayList<UserAccount> allAccount = database.getAllAccounts();
// tableFindFriend is JTable
DefaultTableModel tableModel = (DefaultTableModel) tableFindFriend.getModel();
for(UserAccount acc : allAccount){
    String username = acc.getUsername();
    String fullname = acc.getFullname();
    String email = acc.getEmail();
    String online = "";
    if(acc.isOnline()) online = "online";
    else online = "offline";

    String row[] = {username,fullname,email,online};
    tableModel.addRow(row);
}
```
- **Không chỉnh sửa file DatabaseManagement**, nếu DatabaseManage lỗi hoặc thiếu thì báo cáo và tự viết hàm truy vấn lấy dữ liệu :
```
public ArrayList<UserAccount> getData(){
        DatabaseManagment database = DatabaseManagment.getInstance();   // Lấy database, KHÔNG DÙNG KHỞI TẠO
        Connection connection = database.getConnection();               // Lấy kết nối để thực hiện sql

        String QUERY ="SELECT * FROM USER_ACCOUNT";
        ResultSet data=null;
        ArrayList<UserAccount> myList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY)){
            data = statement.executeQuery();
            
            if(!data.next()){   // Dữ liệu rỗng trả về mảng rỗng
                return myList;
            }
            else{
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    myList.add(account);
                    
                } while (data.next());
                return myList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return myList;
    }
```
## Công việc:
### Bên người dùng
- [X] Đăng ký tài khoản + (cải tiến mã hóa mật khẩu, UI che mật khẩu)
- [X] Đăng nhập tài khoản
- [X] Quên mật khẩu
- [ ] Sửa thông tin, thay đổi mật khẩu
- [X] Hiển thị danh sách bạn bè
- [X] Thêm bạn bè bằng tên đăng nhập
- [X] Hủy kết bạn
- [X] Tạo nhóm chat
- [X] Đổi tên nhóm chat
- [X] Thêm thành viên
- [X] Gán quyền admin
- [X] Xóa thành viên
- [X] Xem lịch sử chat
- [X] Lưu lịch sử tin nhắn
- [X] Tìm kiếm lịch sử chat
- [X] Xóa lịch sử chat
### Bên quản trị
- [X] Xem danh sách cho phép lọc theo tên/tên đăng nhập, sắp xếp theo tên/ngày tạo
- [ ] Thêm/cập nhật/xoá
- [ ] Khoá tài khoản
- [X] Cập nhật mật khẩu
- [X] Xem lịch sử đăng nhập
- [X] Danh sách bạn bè.
- [x] Xem danh sách đăng nhập theo thứ tự thời gian. Thông tin gồm: thời gian, tên đăng nhập, họ tên.
- [X] Xem danh sách các nhóm chat
- [x] Sắp xếp theo tên/thời gian tạo
- [X] Xem danh sách thành viên 1 nhóm
- [X] Xem danh sách admin 1 nhóm.
## Lưu ý:
- Mở VScode ra làm thì trước tiên &rarr; **FETCH**: để kiếm tra trên github có thay đổi gì không (bước này để chuẩn bị backup trước khi pull nếu confilct), nếu có thì **PULL**: lấy code trên github về máy
- Làm xong một chức năng, một hàm rồi (***Không bị lỗi compile***) &rarr; **COMMIT** và **PUSH**
- Chỉ nên làm trên **file mình được giao**, cần chỉnh file khác thì báo trước
- Deadline luôn là tối ngày hôm đó phải có
## Phân công:

### User:

<details>
    <summary> <b>1. Đăng ký tài khoản &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện viết hàm đăng ký tài khoản vào database</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/Register.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>

<details>
    <summary> <b>2. Đăng nhập tài khoản &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện viết hàm đăng nhập tài khoản</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/Login.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>
    
    
<details>
    <summary> <b>3. Hiển thị danh sách bạn bè ở MenuAddFriend &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị danh sách tài khoản chưa kết bạn</li>
                <li>Hiển thị danh sách tài khoản đã kết bạn</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/MenuAddFriend.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>

<details>
    <summary> <b>4. Thực hiện kết bạn và chấp nhận lời mời &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 2 TODO</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/MenuAddFriend.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>5. Thực hiện search bạn bè để thêm vào nhóm ở MenuGroup &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị danh sách bạn bè để thêm vào nhóm </li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/MenuGroup.java</li>
        <li>Deadline: 26/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>6. Thực hiện tạo nhóm &#x2713</b> </summary>
    <ul>
        <li>Người làm: Hiện</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện tạo nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/MenuGroup.java</li>
        <li>Deadline: 24/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>7. Hiển thị thành viên trong nhóm chat</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiên danh sách thành viên của một nhóm</li>
            </ul>
        </li>
        <li>File làm việc: /uichatcomponent/DetailChatGroupForm.java</li>
        <li>Deadline: 24/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>8. Thêm thành viên vào nhóm chat</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện viết hàm thêm thành viên vào nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/DetailChatGroupForm.java</li>
        <li>Deadline: 24/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>9. Gán quyền admin</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Gán quyền admin cho một thành viên</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/DetailChatGroupForm.java</li>
        <li>Deadline: 24/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>10. Xóa thành viên khỏi nhóm</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Xóa thành viên khỏi nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /userchatapp/DetailChatGroupForm.java</li>
        <li>Deadline: 24/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>11. Sửa thông tin tài khoản &#9733</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 3 TODO</li>
            </ul>
        </li>
        <li>File làm việc: /uichatcomponent/ChangeInforForm.java</li>
        <li>Deadline: 30/12/2022</li>
    </ul>
    </details>
    

    
### Admin:
<details>
    <summary> <b>1. Hiển thị danh sách tất cả tài khoản &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị danh sách tài khoản, nạp vào bảng</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuAccountManager.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>2. Lọc danh sách tài khoản theo tên, sắp xếp ở MenuAccountManager &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Lọc theo thanh tìm kiếm tên và các combobox</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuAccountManager.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>3. Xem thông tin chi tiết của Account &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 1 TODO ở MenuAccountManager và 3 TODO ở DetailAccountForm</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuAccountManager.java và uichatcomponent/DetailAccountForm.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>4. Hiển thị danh sách đăng nhập theo thứ tự thời gian &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị lịch sử đăng nhập của tất cả tài khoản</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuLoginHistory.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>5. Hiển thị danh sách tất cả các nhóm chat &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị danh sách các nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuGroupManager.java</li>
        <li>Deadline: 22/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>6. Thực hiện lọc dữ liệu ở MenuGroupManager, MenuLoginHistory &#x2713</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Hiển thị danh sách các nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuGroupManager.java và MenuLoginHistory.java</li>
        <li>Deadline: 26/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>7. Tạo một tài khoản từ admin &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 2 TODO</li>
            </ul>
        </li>
        <li>File làm việc: /uichatcomponent/CreateAccountForm.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>

<details>
    <summary> <b>8. Xem thông tin chi tiết của một nhóm &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 1 TODO ở MenuGroupManager và 1 TODO ở DetailGroupForm</li>
            </ul>
        </li>
        <li>File làm việc: /adminchatapp/MenuGroupManager.java và uichatcomponent/DetailGroupForm.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>9. Gán quyền admin,thay đổi tên nhóm, xóa thành viên &#x2713</b> </summary>
    <ul>
        <li>Người làm: Dũng</li>
        <li>Mô tả:
            <ul>
                <li>Thực hiện 3 TODO</li>
            </ul>
        </li>
        <li>File làm việc:  uichatcomponent/DetailGroupChatForm.java</li>
        <li>Deadline: 29/12/2022</li>
    </ul>
    </details>
    
<details>
    <summary> <b>10. Khóa,Xóa và chỉnh sửa tài khoản &#9733</b> </summary>
    <ul>
        <li>Người làm: </li>
        <li>Mô tả:
            <ul>
                <li>Xóa thành viên khỏi nhóm chat</li>
            </ul>
        </li>
        <li>File làm việc: /uichatcomponent/DetailChatGroupForm.java</li>
        <li>Deadline: 30/12/2022</li>
    </ul>
    </details>

