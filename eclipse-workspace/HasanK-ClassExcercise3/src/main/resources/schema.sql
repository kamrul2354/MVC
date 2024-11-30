-- Drop the table if it already exists
DROP TABLE IF EXISTS contact;

-- Create the `contact` table
CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Auto-increment primary key
    name VARCHAR(100) NOT NULL,        -- Name of the contact
    email VARCHAR(100) NOT NULL,       -- Email of the contact
    phone VARCHAR(15) NOT NULL         -- Phone number of the contact
);
