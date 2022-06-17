INSERT INTO tasks (description, name, owner_email, programming_lang, programming_lang_version, workspace_url)
VALUES
    ('description1', 'name1', 'owneremail1', 'java', '17', 'admin_url1'),
    ('description2', 'name2', 'owneremail2', 'python', '7', 'admin_python_url');

INSERT INTO assignments (assigned_on, deadline, user_id, user_workspace_url, task_id)
VALUES ('2020-12-12', '2020-12-12', 1, 'user.workspace0', 1),
('2021-12-12', '2020-11-12', 2, 'user.workspace1', 1),
('2021-11-10', '2020-01-12', 1, 'user.workspace2', 2)
