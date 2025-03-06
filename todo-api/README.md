# todo
## 画面
1. 

## 機能
1. Create
2. Read
3. Update
4. Delete

## テーブル定義
- todo テーブル
  - id
    - primary
  - title
    - not null
  - status
    - not null
  - user
    - not null
  - createdAt
    - not null
  - updatedAt
    - not null

## その他
- テストコード作る

psql -h db -p 5432 -U postgres -d postgres