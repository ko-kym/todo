CREATE TABLE todo (
    id UUID PRIMARY KEY, -- 一意のタスク識別子
    title TEXT NOT NULL, -- タスクのタイトル
    status TEXT NOT NULL, -- タスクの状態
    created_at TIMESTAMP NOT NULL, -- タスクの作成日時
    updated_at TIMESTAMP NOT NULL -- タスクの最終更新日時
);
COMMENT ON COLUMN todo.id IS '一意のタスク識別子';
COMMENT ON COLUMN todo.title IS 'タスクのタイトル';
COMMENT ON COLUMN todo.status IS 'タスクの状態';
COMMENT ON COLUMN todo.created_at IS 'タスクの作成日時';
COMMENT ON COLUMN todo.updated_at IS 'タスクの最終更新日時';