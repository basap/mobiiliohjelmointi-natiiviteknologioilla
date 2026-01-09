package com.example.viikko1.domain

val mockTasks = listOf(
    Task(
        id = 1,
        title = "Mene lenkille",
        description = "Siellä on kylmä",
        priority = 1,
        dueDate = "2026-01-08",
        done = false
    ),
    Task(
        id = 2,
        title = "Mene kauppaan",
        description = "Jääkaappi on tyhjä",
        priority = 2,
        dueDate = "2026-01-09",
        done = false
    ),
    Task(
        id = 3,
        title = "Mene kouluun",
        description = "Edistämään opintoja",
        priority = 3,
        dueDate = "2026-01-10",
        done = true
    ),
    Task(
        id = 4,
        title = "Mene kotiin",
        description = "Reflektoimaan päivän kokemuksia",
        priority = 2,
        dueDate = "2026-01-11",
        done = false
    ),
    Task(
        id = 5,
        title = "Mene töihin",
        description = "Koska rahat on loppu",
        priority = 1,
        dueDate = "2026-01-12",
        done = true
    )
)
