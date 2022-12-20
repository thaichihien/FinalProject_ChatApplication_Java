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
## Hướng dẫn tạo cơ sở dự liệu Postgres:
- Đọc hướng dẫn chi tiết tại thư mục ***doc***
## Hướng dẫn test code:
### Đối với chức năng Login và Register:
- Viết code tại các file Login.java Register.java. Chạy code ở các file này
- Code thành công khi vào trang chủ có hiển thị tên tài khoản **(username)** ở góc trên bên trái
### Đối với chức năng ở các Menu Chat,AddFriend,Group
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
## Công việc:
### Bên người dùng
- [ ] Đăng ký tài khoản
- [ ] Đăng nhập tài khoản
- [ ] Quên mật khẩu
- [ ] Hiển thị danh sách bạn bè
- [ ] Thêm bạn bè bằng tên đăng nhập
- [ ] Hủy kết bạn
- [ ] Tạo nhóm chat
- [ ] Đổi tên nhóm chat
- [ ] Thêm thành viên
- [ ] Gán quyền admin
- [ ] Xóa thành viên
### Bên quản trị
- [ ] Xem danh sách cho phép lọc theo tên/tên đăng nhập, sắp xếp theo tên/ngày tạo
- [ ] Thêm/cập nhật/xoá
- [ ] Khoá tài khoản
- [ ] Cập nhật mật khẩu
- [ ] Xem lịch sử đăng nhập
- [ ] Danh sách bạn bè.
- [ ] Xem danh sách đăng nhập theo thứ tự thời gian. Thông tin gồm: thời gian, tên đăng nhập, họ tên.
- [ ] Xem danh sách các nhóm chat
- [ ] Sắp xếp theo tên/thời gian tạo
- [ ] Xem danh sách thành viên 1 nhóm
- [ ] Xem danh sách admin 1 nhóm.
## Lưu ý:
- Mở VScode ra làm thì trước tiên &rarr; **FETCH**: để kiếm tra trên github có thay đổi gì không (bước này để chuẩn bị backup trước khi pull nếu confilct), nếu có thì **PULL**: lấy code trên github về máy
- Làm xong một chức năng, một hàm rồi (***Không bị lỗi compile***) &rarr; **COMMIT** và **PUSH**
- Chỉ nên làm trên **file mình được giao**, cần chỉnh file khác thì báo trước
- Deadline luôn là tối ngày hôm đó phải có
- 