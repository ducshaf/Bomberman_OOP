# Bài tập lớn OOP - Bomberman Game

ENTITY
- Animated Entity
   + Enemy có 4 loại:
      Ghost có thể đi xuyên tường, AI randomDirection, có khả năng dịch chuyển sang 1 tile bất kì trong bản đồ.
      Evil Bomb sử dụng hàm A* (tính cả Destroyable Wall) để tính đường ngắn nhất đên player, khi gặp DestoyableWall sẽ đặt bom, dùng hàm findSafePlace (dijkstra's algorithm đơn            giản) để tìm tile gần nhất an toàn, dùng A* để tìm đường đến đó. Nếu Evil bomb đến cột hoặc dòng cùng player thì tự nổ tung (fierce bomb).
      Slime di chuyển bằng A*(k tính DestroyableWall) để tìm đường đến player, nếu k thể đến được thì di chuyển bằng randomDirection.
      Snow di chuyển ngẫu nhiên bằng randomDirection.
   + Bomber
   + Bomb & Explosion
      Ấn H để đặt bom.
      Trong bomb có các hàm để di chuyển khi sử dụng powerup The force, nổ hẹn giờ khi dùng TimeBomb, nổ vượt tất cả DestroyableWall khi dùng FierceBomb.
      Bom nổ sau 2.5s, k có animation cho Bomb và Explosion.
      
    Va chạm dùng canMove, và dùng 2 mảng entities, 1 mảng entities để Grass, DestroyableWall, Wall, 1 mảng mobileEntities để AnimatedEntity
- StatusEffect
   + Agile: tăng tốc độ di chuyển của player, mặc định 0.8 (hiển thị 8) mỗi lần ăn tăng 0.1.
   + Blind: Tạo 1 overlay đen để làm mù, vẫn gặp bug khi gameover lúc có debuff này.
   + FierceBomb: bomb nổ vượt qua DestroyableWall, bomblevel = 15.
   + FreezeTime: dừng thời gian, player vẫn có thể di chuyển.
   + Heal: tăng máu nếu máu thấp hơn 3.
   + IncrementBombLevel: tăng phạm vi nổ bom.
   + IncrementBombNumber: tăng số bom có thể đặt.
   + Inversion: đảo chiều điều khiển của người chơi.
   + Percolate: lưu vị trí hiện tại của người chơi. Nếu đến cuối thời gian người chơi vẫn trong tường thì quay lại vị trí đã lưu.
   + Random: 1 status effect random.
   + Slow: làm chậm tốc độ của player xuống 0.5.
   + TimeBomb: bom hẹn giờ, ấn K để nổ.
   
MAP
- hàm generateMap để tạo 1 map random, đặt vị trí của player, Wall và random Enemy.
- hàm inputMap để lấy map từ file Level1.txt, chủ yếu dùng để debug.

GRAPHICS lấy từ openart, dùng Tiled để tạo ground map, overlay, rồi chuyển qua png.

GUI
Pause tạm dừng trò chơi, ấn Resume hoặc ngoài để tiếp tục
Main Menu chuyển về scene App

   
