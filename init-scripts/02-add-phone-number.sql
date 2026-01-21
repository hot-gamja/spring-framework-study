-- 추가: 기존 데이터베이스에 phone_number 컬럼을 추가하는 마이그레이션 스크립트
-- Add phone_number column to existing users table
-- This migration script is for existing databases that don't have the phone_number column

-- Check if column exists and add it if it doesn't
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'users'
        AND column_name = 'phone_number'
    ) THEN
        ALTER TABLE users ADD COLUMN phone_number VARCHAR(20); -- 추가: 전화번호 컬럼
        RAISE NOTICE 'Column phone_number added to users table';
    ELSE
        RAISE NOTICE 'Column phone_number already exists in users table';
    END IF;
END $$;
