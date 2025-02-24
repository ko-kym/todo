CREATE TABLE todos (
    id TEXT PRIMARY KEY, -- 一意のタスク識別子
    title TEXT NOT NULL, -- タスクのタイトル
    status TEXT NOT NULL, -- タスクの状態
    created_at TIMESTAMP NOT NULL, -- タスクの作成日時
    updated_at TIMESTAMP NOT NULL -- タスクの最終更新日時
);
COMMENT ON COLUMN todos.id IS '一意のタスク識別子';
COMMENT ON COLUMN todos.title IS 'タスクのタイトル';
COMMENT ON COLUMN todos.status IS 'タスクの状態';
COMMENT ON COLUMN todos.created_at IS 'タスクの作成日時';
COMMENT ON COLUMN todos.updated_at IS 'タスクの最終更新日時';