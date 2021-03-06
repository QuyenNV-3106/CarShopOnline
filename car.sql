USE master
go
CREATE DATABASE [CarShopManagement]
go
USE [CarShopManagement]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[categoryID] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderDetails]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderDetails](
	[orderID] [varchar](50) NULL,
	[productID] [nvarchar](10) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[orderID] [varchar](50) NOT NULL,
	[userID] [nvarchar](250) NULL,
	[orderDate] [datetime] NULL,
	[address] [nvarchar](max) NULL,
	[userName] [nvarchar](250) NULL,
	[email] [nvarchar](250) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[productID] [nvarchar](10) NOT NULL,
	[productName] [nvarchar](250) NULL,
	[detail] [nvarchar](max) NULL,
	[img] [nvarchar](250) NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[categoryID] [nvarchar](50) NULL,
	[sale] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roleuser]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roleuser](
	[roleID] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 8/24/2021 4:53:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[userID] [nvarchar](250) NOT NULL,
	[password] [varchar](50) NULL,
	[name] [nvarchar](250) NULL,
	[role] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Categories] ([categoryID]) VALUES (N'Audi')
INSERT [dbo].[Categories] ([categoryID]) VALUES (N'BMW')
INSERT [dbo].[Categories] ([categoryID]) VALUES (N'Mercedes')
INSERT [dbo].[Categories] ([categoryID]) VALUES (N'Porsche')
INSERT [dbo].[Categories] ([categoryID]) VALUES (N'Volkswagen')
GO
INSERT [dbo].[Products] ([productID], [productName], [detail], [img], [quantity], [price], [categoryID], [sale]) VALUES (N'AA111', N'111111', N'1', N'car.jpg', 100, 1, N'Porsche', 1)
INSERT [dbo].[Products] ([productID], [productName], [detail], [img], [quantity], [price], [categoryID], [sale]) VALUES (N'AA11111111', N'12321', N'111', N'car.jpg', 1, 1, N'Mercedes', 1)
INSERT [dbo].[Products] ([productID], [productName], [detail], [img], [quantity], [price], [categoryID], [sale]) VALUES (N'AQ112', N'12331', N'9', N'car-backgr.jpg', 9, 9, N'Audi', 1)
GO
INSERT [dbo].[roleuser] ([roleID]) VALUES (N'AD')
INSERT [dbo].[roleuser] ([roleID]) VALUES (N'US')
GO
INSERT [dbo].[users] ([userID], [password], [name], [role]) VALUES (N'admin', N'1234', N'hello', N'AD')
INSERT [dbo].[users] ([userID], [password], [name], [role]) VALUES (N'user123', N'12345', N'se12345', N'US')
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD  CONSTRAINT [orderID] FOREIGN KEY([orderID])
REFERENCES [dbo].[orders] ([orderID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[orderDetails] CHECK CONSTRAINT [orderID]
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD  CONSTRAINT [productID] FOREIGN KEY([productID])
REFERENCES [dbo].[Products] ([productID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[orderDetails] CHECK CONSTRAINT [productID]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [userID] FOREIGN KEY([userID])
REFERENCES [dbo].[users] ([userID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [userID]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [categoryid] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Categories] ([categoryID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [categoryid]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [role] FOREIGN KEY([role])
REFERENCES [dbo].[roleuser] ([roleID])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [role]
GO
