# 概要

保護ねこと里親をつなぐプログラムを作成します。
データベースの情報にPOST機能を付設後、Validationを行うようにしました。

## 起動手順

catsテーブルのレコード初期値は以下のとおりです。

```mysql
mysql> select * from cats;
+----+--------+--------+-----+
| id | name   | sex    | age |
+----+--------+--------+-----+
|  1 | Omochi | female |   2 |
|  2 | Coa    | male   |   3 |
|  3 | Gonchi | male   |   5 |
+----+--------+--------+-----+
3 rows in set (0.01 sec)


```

## POST機能のValidation

保護ねこの名前、性別、年齢は必須項目としました。
![スクリーンショット 2024-07-21 14 42 47](https://github.com/user-attachments/assets/56d2c575-8417-46d8-92b2-1b21082e3928)
![スクリーンショット 2024-07-21 14 47 56](https://github.com/user-attachments/assets/1c7ed9fe-ea76-43c8-83d6-b65796ca9cb9)
![スクリーンショット 2024-07-21 14 47 28](https://github.com/user-attachments/assets/44fdf4e8-b063-4283-8c7c-debf31da2003)

完了
