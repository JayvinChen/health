/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : health

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 14/10/2019 11:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_businessreport
-- ----------------------------
DROP TABLE IF EXISTS `t_businessreport`;
CREATE TABLE `t_businessreport`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `todayNewMember` int(10) NOT NULL,
  `totalMember` int(10) NOT NULL,
  `thisWeekNewMember` int(10) NOT NULL,
  `thisMonthNewMember` int(10) NOT NULL,
  `todayOrderNumber` int(10) NOT NULL,
  `todayVisitsNumber` int(10) NOT NULL,
  `thisWeekOrderNumber` int(10) NOT NULL,
  `thisWeekVisitsNumber` int(10) NOT NULL,
  `thisMonthOrderNumber` int(10) NOT NULL,
  `thisMonthVisitsNumber` int(10) NOT NULL,
  `reportDate` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_businessreport
-- ----------------------------
INSERT INTO `t_businessreport` VALUES (3, 0, 12, 3, 3, 26, 11, 27, 12, 30, 12, '2019-10-12');

-- ----------------------------
-- Table structure for t_checkgroup
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup`;
CREATE TABLE `t_checkgroup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `helpCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attention` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_checkgroup
-- ----------------------------
INSERT INTO `t_checkgroup` VALUES (5, '0001', '一般检查', 'YBJC', '0', '一般检查', '无');
INSERT INTO `t_checkgroup` VALUES (6, '0002', '视力色觉', 'SLSJ', '0', '视力色觉', NULL);
INSERT INTO `t_checkgroup` VALUES (7, '0003', '血常规', 'XCG', '0', '血常规', NULL);
INSERT INTO `t_checkgroup` VALUES (8, '0004', '尿常规', 'NCG', '0', '尿常规', NULL);
INSERT INTO `t_checkgroup` VALUES (9, '0005', '肝功三项', 'GGSX', '0', '肝功三项', NULL);
INSERT INTO `t_checkgroup` VALUES (10, '0006', '肾功三项', 'NGSX', '0', '肾功三项', NULL);
INSERT INTO `t_checkgroup` VALUES (11, '0007', '血脂四项', 'XZSX', '0', '血脂四项', NULL);
INSERT INTO `t_checkgroup` VALUES (12, '0008', '心肌酶三项', 'XJMSX', '0', '心肌酶三项', NULL);
INSERT INTO `t_checkgroup` VALUES (13, '0009', '甲功三项', 'JGSX', '0', '甲功三项', NULL);
INSERT INTO `t_checkgroup` VALUES (14, '0010', '子宫附件彩超', 'ZGFJCC', '2', '子宫附件彩超', NULL);
INSERT INTO `t_checkgroup` VALUES (15, '0011', '胆红素三项', 'DHSSX', '0', '胆红素三项', NULL);
INSERT INTO `t_checkgroup` VALUES (23, '1415', '啦啦啦', 'lll', '1', '不能吃早餐', '早餐吃不得');
INSERT INTO `t_checkgroup` VALUES (24, '1155', '哈哈哈', 'hhh', '2', '不能空腹吃早餐', '无');

-- ----------------------------
-- Table structure for t_checkgroup_checkitem
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup_checkitem`;
CREATE TABLE `t_checkgroup_checkitem`  (
  `checkgroup_id` int(11) NOT NULL DEFAULT 0,
  `checkitem_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`checkgroup_id`, `checkitem_id`) USING BTREE,
  INDEX `item_id`(`checkitem_id`) USING BTREE,
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_checkgroup_checkitem
-- ----------------------------
INSERT INTO `t_checkgroup_checkitem` VALUES (23, 28);
INSERT INTO `t_checkgroup_checkitem` VALUES (5, 29);
INSERT INTO `t_checkgroup_checkitem` VALUES (5, 30);
INSERT INTO `t_checkgroup_checkitem` VALUES (5, 31);
INSERT INTO `t_checkgroup_checkitem` VALUES (5, 32);
INSERT INTO `t_checkgroup_checkitem` VALUES (5, 33);
INSERT INTO `t_checkgroup_checkitem` VALUES (6, 33);
INSERT INTO `t_checkgroup_checkitem` VALUES (6, 34);
INSERT INTO `t_checkgroup_checkitem` VALUES (6, 35);
INSERT INTO `t_checkgroup_checkitem` VALUES (6, 36);
INSERT INTO `t_checkgroup_checkitem` VALUES (6, 37);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 38);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 39);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 40);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 41);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 42);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 43);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 44);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 45);
INSERT INTO `t_checkgroup_checkitem` VALUES (23, 45);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 46);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 47);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 48);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 49);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 50);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 51);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 52);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 53);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 54);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 55);
INSERT INTO `t_checkgroup_checkitem` VALUES (7, 56);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 57);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 58);
INSERT INTO `t_checkgroup_checkitem` VALUES (23, 58);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 59);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 60);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 61);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 62);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 63);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 64);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 65);
INSERT INTO `t_checkgroup_checkitem` VALUES (24, 65);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 66);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 67);
INSERT INTO `t_checkgroup_checkitem` VALUES (23, 67);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 68);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 69);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 70);
INSERT INTO `t_checkgroup_checkitem` VALUES (8, 71);
INSERT INTO `t_checkgroup_checkitem` VALUES (9, 72);
INSERT INTO `t_checkgroup_checkitem` VALUES (9, 73);
INSERT INTO `t_checkgroup_checkitem` VALUES (9, 74);
INSERT INTO `t_checkgroup_checkitem` VALUES (10, 75);
INSERT INTO `t_checkgroup_checkitem` VALUES (24, 75);
INSERT INTO `t_checkgroup_checkitem` VALUES (10, 76);
INSERT INTO `t_checkgroup_checkitem` VALUES (10, 77);
INSERT INTO `t_checkgroup_checkitem` VALUES (11, 78);
INSERT INTO `t_checkgroup_checkitem` VALUES (11, 79);
INSERT INTO `t_checkgroup_checkitem` VALUES (11, 80);
INSERT INTO `t_checkgroup_checkitem` VALUES (11, 81);
INSERT INTO `t_checkgroup_checkitem` VALUES (12, 82);
INSERT INTO `t_checkgroup_checkitem` VALUES (24, 82);
INSERT INTO `t_checkgroup_checkitem` VALUES (12, 83);
INSERT INTO `t_checkgroup_checkitem` VALUES (12, 84);
INSERT INTO `t_checkgroup_checkitem` VALUES (13, 85);
INSERT INTO `t_checkgroup_checkitem` VALUES (13, 86);
INSERT INTO `t_checkgroup_checkitem` VALUES (13, 87);
INSERT INTO `t_checkgroup_checkitem` VALUES (14, 88);
INSERT INTO `t_checkgroup_checkitem` VALUES (14, 89);
INSERT INTO `t_checkgroup_checkitem` VALUES (15, 90);
INSERT INTO `t_checkgroup_checkitem` VALUES (15, 91);
INSERT INTO `t_checkgroup_checkitem` VALUES (15, 92);

-- ----------------------------
-- Table structure for t_checkitem
-- ----------------------------
DROP TABLE IF EXISTS `t_checkitem`;
CREATE TABLE `t_checkitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_checkitem
-- ----------------------------
INSERT INTO `t_checkitem` VALUES (28, '0001', '身高', '0', '0-100', 5, '1', '无', '身高');
INSERT INTO `t_checkitem` VALUES (29, '0002', '体重', '0', '0-100', 5, '1', '无', '体重');
INSERT INTO `t_checkitem` VALUES (30, '0003', '体重指数', '0', '0-100', 5, '1', '无', '体重指数');
INSERT INTO `t_checkitem` VALUES (31, '0004', '收缩压', '0', '0-100', 5, '1', '无', '收缩压');
INSERT INTO `t_checkitem` VALUES (32, '0005', '舒张压', '0', '0-100', 5, '1', '无', '舒张压');
INSERT INTO `t_checkitem` VALUES (33, '0006', '裸视力（右）', '0', '0-100', 5, '1', '无', '裸视力（右）');
INSERT INTO `t_checkitem` VALUES (34, '0007', '裸视力（左）', '0', '0-100', 5, '1', '无', '裸视力（左）');
INSERT INTO `t_checkitem` VALUES (35, '0008', '矫正视力（右）', '0', '0-100', 5, '1', '无', '矫正视力（右）');
INSERT INTO `t_checkitem` VALUES (36, '0009', '矫正视力（左）', '0', '0-100', 5, '1', '无', '矫正视力（左）');
INSERT INTO `t_checkitem` VALUES (37, '0010', '色觉', '0', '0-100', 5, '1', '无', '色觉');
INSERT INTO `t_checkitem` VALUES (38, '0011', '白细胞计数', '0', '0-100', 10, '2', '无', '白细胞计数');
INSERT INTO `t_checkitem` VALUES (39, '0012', '红细胞计数', '0', '0-100', 10, '2', NULL, '红细胞计数');
INSERT INTO `t_checkitem` VALUES (40, '0013', '血红蛋白', '0', '0-100', 10, '2', NULL, '血红蛋白');
INSERT INTO `t_checkitem` VALUES (41, '0014', '红细胞压积', '0', '0-100', 10, '2', NULL, '红细胞压积');
INSERT INTO `t_checkitem` VALUES (42, '0015', '平均红细胞体积', '0', '0-100', 10, '2', NULL, '平均红细胞体积');
INSERT INTO `t_checkitem` VALUES (43, '0016', '平均红细胞血红蛋白含量', '0', '0-100', 10, '2', NULL, '平均红细胞血红蛋白含量');
INSERT INTO `t_checkitem` VALUES (44, '0017', '平均红细胞血红蛋白浓度', '0', '0-100', 10, '2', NULL, '平均红细胞血红蛋白浓度');
INSERT INTO `t_checkitem` VALUES (45, '0018', '红细胞分布宽度-变异系数', '0', '0-100', 10, '2', NULL, '红细胞分布宽度-变异系数');
INSERT INTO `t_checkitem` VALUES (46, '0019', '血小板计数', '0', '0-100', 10, '2', NULL, '血小板计数');
INSERT INTO `t_checkitem` VALUES (47, '0020', '平均血小板体积', '0', '0-100', 10, '2', NULL, '平均血小板体积');
INSERT INTO `t_checkitem` VALUES (48, '0021', '血小板分布宽度', '0', '0-100', 10, '2', NULL, '血小板分布宽度');
INSERT INTO `t_checkitem` VALUES (49, '0022', '淋巴细胞百分比', '0', '0-100', 10, '2', NULL, '淋巴细胞百分比');
INSERT INTO `t_checkitem` VALUES (50, '0023', '中间细胞百分比', '0', '0-100', 10, '2', NULL, '中间细胞百分比');
INSERT INTO `t_checkitem` VALUES (51, '0024', '中性粒细胞百分比', '0', '0-100', 10, '2', NULL, '中性粒细胞百分比');
INSERT INTO `t_checkitem` VALUES (52, '0025', '淋巴细胞绝对值', '0', '0-100', 10, '2', NULL, '淋巴细胞绝对值');
INSERT INTO `t_checkitem` VALUES (53, '0026', '中间细胞绝对值', '0', '0-100', 10, '2', NULL, '中间细胞绝对值');
INSERT INTO `t_checkitem` VALUES (54, '0027', '中性粒细胞绝对值', '0', '0-100', 10, '2', NULL, '中性粒细胞绝对值');
INSERT INTO `t_checkitem` VALUES (55, '0028', '红细胞分布宽度-标准差', '0', '0-100', 10, '2', NULL, '红细胞分布宽度-标准差');
INSERT INTO `t_checkitem` VALUES (56, '0029', '血小板压积', '0', '0-100', 10, '2', NULL, '血小板压积');
INSERT INTO `t_checkitem` VALUES (57, '0030', '尿比重', '0', '0-100', 10, '2', NULL, '尿比重');
INSERT INTO `t_checkitem` VALUES (58, '0031', '尿酸碱度', '0', '0-100', 10, '2', NULL, '尿酸碱度');
INSERT INTO `t_checkitem` VALUES (59, '0032', '尿白细胞', '0', '0-100', 10, '2', NULL, '尿白细胞');
INSERT INTO `t_checkitem` VALUES (60, '0033', '尿亚硝酸盐', '0', '0-100', 10, '2', NULL, '尿亚硝酸盐');
INSERT INTO `t_checkitem` VALUES (61, '0034', '尿蛋白质', '0', '0-100', 10, '2', NULL, '尿蛋白质');
INSERT INTO `t_checkitem` VALUES (62, '0035', '尿糖', '0', '0-100', 10, '2', NULL, '尿糖');
INSERT INTO `t_checkitem` VALUES (63, '0036', '尿酮体', '0', '0-100', 10, '2', NULL, '尿酮体');
INSERT INTO `t_checkitem` VALUES (64, '0037', '尿胆原', '0', '0-100', 10, '2', NULL, '尿胆原');
INSERT INTO `t_checkitem` VALUES (65, '0038', '尿胆红素', '0', '0-100', 10, '2', NULL, '尿胆红素');
INSERT INTO `t_checkitem` VALUES (66, '0039', '尿隐血', '0', '0-100', 10, '2', NULL, '尿隐血');
INSERT INTO `t_checkitem` VALUES (67, '0040', '尿镜检红细胞', '0', '0-100', 10, '2', NULL, '尿镜检红细胞');
INSERT INTO `t_checkitem` VALUES (68, '0041', '尿镜检白细胞', '0', '0-100', 10, '2', NULL, '尿镜检白细胞');
INSERT INTO `t_checkitem` VALUES (69, '0042', '上皮细胞', '0', '0-100', 10, '2', NULL, '上皮细胞');
INSERT INTO `t_checkitem` VALUES (70, '0043', '无机盐类', '0', '0-100', 10, '2', NULL, '无机盐类');
INSERT INTO `t_checkitem` VALUES (71, '0044', '尿镜检蛋白定性', '0', '0-100', 10, '2', NULL, '尿镜检蛋白定性');
INSERT INTO `t_checkitem` VALUES (72, '0045', '丙氨酸氨基转移酶', '0', '0-100', 10, '2', NULL, '丙氨酸氨基转移酶');
INSERT INTO `t_checkitem` VALUES (73, '0046', '天门冬氨酸氨基转移酶', '0', '0-100', 10, '2', NULL, '天门冬氨酸氨基转移酶');
INSERT INTO `t_checkitem` VALUES (74, '0047', 'Y-谷氨酰转移酶', '0', '0-100', 10, '2', NULL, 'Y-谷氨酰转移酶');
INSERT INTO `t_checkitem` VALUES (75, '0048', '尿素', '0', '0-100', 10, '2', NULL, '尿素');
INSERT INTO `t_checkitem` VALUES (76, '0049', '肌酐', '0', '0-100', 10, '2', NULL, '肌酐');
INSERT INTO `t_checkitem` VALUES (77, '0050', '尿酸', '0', '0-100', 10, '2', NULL, '尿酸');
INSERT INTO `t_checkitem` VALUES (78, '0051', '总胆固醇', '0', '0-100', 10, '2', NULL, '总胆固醇');
INSERT INTO `t_checkitem` VALUES (79, '0052', '甘油三酯', '0', '0-100', 10, '2', NULL, '甘油三酯');
INSERT INTO `t_checkitem` VALUES (80, '0053', '高密度脂蛋白胆固醇', '0', '0-100', 10, '2', NULL, '高密度脂蛋白胆固醇');
INSERT INTO `t_checkitem` VALUES (81, '0054', '低密度脂蛋白胆固醇', '0', '0-100', 10, '2', NULL, '低密度脂蛋白胆固醇');
INSERT INTO `t_checkitem` VALUES (82, '0055', '磷酸肌酸激酶', '0', '0-100', 10, '2', NULL, '磷酸肌酸激酶');
INSERT INTO `t_checkitem` VALUES (83, '0056', '磷酸肌酸激酶同工酶', '0', '0-100', 10, '2', NULL, '磷酸肌酸激酶同工酶');
INSERT INTO `t_checkitem` VALUES (84, '0057', '乳酸脱氢酶', '0', '0-100', 10, '2', NULL, '乳酸脱氢酶');
INSERT INTO `t_checkitem` VALUES (85, '0058', '三碘甲状腺原氨酸', '0', '0-100', 10, '2', NULL, '三碘甲状腺原氨酸');
INSERT INTO `t_checkitem` VALUES (86, '0059', '甲状腺素', '0', '0-100', 10, '2', NULL, '甲状腺素');
INSERT INTO `t_checkitem` VALUES (87, '0060', '促甲状腺激素', '0', '0-100', 10, '2', NULL, '促甲状腺激素');
INSERT INTO `t_checkitem` VALUES (88, '0061', '子宫', '2', '0-100', 10, '2', NULL, '子宫');
INSERT INTO `t_checkitem` VALUES (89, '0062', '附件', '2', '0-100', 10, '2', NULL, '附件');
INSERT INTO `t_checkitem` VALUES (90, '0063', '总胆红素', '0', '0-100', 10, '2', NULL, '总胆红素');
INSERT INTO `t_checkitem` VALUES (91, '0064', '直接胆红素', '0', '0-100', 10, '2', NULL, '直接胆红素');
INSERT INTO `t_checkitem` VALUES (92, '0065', '间接胆红素', '0', '0-100', 10, '2', NULL, '间接胆红素');
INSERT INTO `t_checkitem` VALUES (99, '001', '啦啦啦', '2', '22-40', 150, '1', '哈哈哈', '就检查检查..');
INSERT INTO `t_checkitem` VALUES (102, '002', '啦啦啦', '0', '22', 520, '2', '好啦啦', 'lll');

-- ----------------------------
-- Table structure for t_hotpackage
-- ----------------------------
DROP TABLE IF EXISTS `t_hotpackage`;
CREATE TABLE `t_hotpackage`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(10) NOT NULL,
  `proportion` double(255, 2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reportDate` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_hotpackage
-- ----------------------------
INSERT INTO `t_hotpackage` VALUES (5, '孕前检查', 6, 0.20, '呵呵呵呵', '2019-10-12');
INSERT INTO `t_hotpackage` VALUES (6, '粉红珍爱(女)升级TM12项筛查体检套餐', 6, 0.20, '本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。', '2019-10-12');
INSERT INTO `t_hotpackage` VALUES (7, '入职无忧体检套餐（男女通用）', 5, 0.17, '入职体检套餐', '2019-10-12');
INSERT INTO `t_hotpackage` VALUES (8, '阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐', 5, 0.17, '本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查', '2019-10-12');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idCard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regTime` date NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (82, NULL, '小明', '1', '123456789000999999', '18511279942', '2019-03-08', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (83, NULL, '王美丽', '1', '132333333333333', '13412345678', '2019-03-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (92, NULL, '333', '2', '234234145432121345', '18019286521', '2019-04-19', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (96, NULL, '皮皮', '1', '441701199502140044', '13719092593', '2019-10-07', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (97, NULL, '小花', '2', '441701199502140044', '13068692478', '2019-10-07', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (98, NULL, NULL, '2', NULL, '15800035248', '2019-10-08', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (99, NULL, NULL, '1', NULL, '13411125242', '2019-09-25', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (100, NULL, NULL, '2', NULL, '13522233333', '2019-08-24', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (101, NULL, NULL, '2', NULL, '13411166622', '2019-07-25', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (102, NULL, NULL, '2', NULL, '13815236925', '2019-04-23', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (103, NULL, NULL, '1', NULL, '13511133322', '2019-05-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (104, NULL, NULL, '2', NULL, '13477702302', '2019-06-15', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkUrl` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentMenuId` int(11) NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`parentMenuId`) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '会员管理', NULL, '2', 2, 'fa-user-md', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (2, '会员档案', 'member.html', '/2-1', 1, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (3, '体检上传', NULL, '/2-2', 2, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (4, '会员统计', NULL, '/2-3', 3, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (5, '预约管理', NULL, '3', 3, 'fa-tty', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (6, '预约列表', 'ordersettinglist.html', '/3-1', 1, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (7, '预约设置', 'ordersetting.html', '/3-2', 2, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (8, '套餐管理', 'package.html', '/3-3', 3, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (9, '检查组管理', 'checkgroup.html', '/3-4', 4, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (10, '检查项管理', 'checkitem.html', '/3-5', 5, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (11, '健康评估', NULL, '4', 4, 'fa-stethoscope', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (12, '中医体质辨识', NULL, '/4-1', 1, NULL, NULL, 11, 2);
INSERT INTO `t_menu` VALUES (13, '统计分析', NULL, '5', 5, 'fa-heartbeat', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (14, '会员数量', 'report_member.html', '/5-1', 1, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (15, '系统设置', NULL, '6', 6, 'fa-users', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (16, '菜单管理', 'menu.html', '/6-1', 1, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (17, '权限管理', 'permission.html', '/6-2', 2, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (18, '角色管理', 'role.html', '/6-3', 3, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (19, '用户管理', 'user.html', '/6-4', 4, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (20, '套餐占比', 'report_package.html', '/5-2', 2, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (21, '运营数据', 'report_business.html', '/5-3', 3, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (23, '工作台', NULL, '1', 1, 'fa-dashboard', NULL, NULL, 1);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT '员会id',
  `orderDate` date NULL DEFAULT NULL COMMENT '约预日期',
  `orderType` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约状态（是否到诊）',
  `package_id` int(11) NULL DEFAULT NULL COMMENT '餐套id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_member_id`(`member_id`) USING BTREE,
  INDEX `key_package_id`(`package_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (18, 82, '2019-10-08', '微信预约', '已到诊', 12);
INSERT INTO `t_order` VALUES (19, 96, '2019-10-13', '微信预约', '未到诊', 12);
INSERT INTO `t_order` VALUES (20, 96, '2019-10-15', '微信预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (21, 97, '2019-10-16', '微信预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (33, 99, '2019-10-12', '电话预约', '已到诊', 15);
INSERT INTO `t_order` VALUES (34, 100, '2019-10-12', '微信预约', '已到诊', 15);
INSERT INTO `t_order` VALUES (35, 97, '2019-10-12', '微信预约', '未到诊', 14);
INSERT INTO `t_order` VALUES (36, 96, '2019-10-12', '电话预约', '未到诊', 12);
INSERT INTO `t_order` VALUES (37, 93, '2019-10-12', '电话预约', '已到诊', 14);
INSERT INTO `t_order` VALUES (38, 104, '2019-10-12', '电话预约', '未到诊', 16);
INSERT INTO `t_order` VALUES (39, 97, '2019-10-12', '微信预约', '已到诊', 15);
INSERT INTO `t_order` VALUES (40, 103, '2019-10-12', '电话预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (41, 102, '2019-10-12', '微信预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (42, 101, '2019-10-12', '电话预约', '未到诊', 18);
INSERT INTO `t_order` VALUES (43, 98, '2019-10-12', '微信预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (44, 99, '2019-10-12', '微信预约', '已到诊', 18);
INSERT INTO `t_order` VALUES (45, 98, '2019-10-12', '微信预约', '未到诊', 18);
INSERT INTO `t_order` VALUES (46, 98, '2019-10-12', '电话预约', '未到诊', 14);
INSERT INTO `t_order` VALUES (47, 104, '2019-10-12', '电话预约', '已到诊', 12);
INSERT INTO `t_order` VALUES (48, 99, '2019-10-12', '电话预约', '未到诊', 13);
INSERT INTO `t_order` VALUES (49, 101, '2019-10-12', '电话预约', '未到诊', 18);
INSERT INTO `t_order` VALUES (50, 101, '2019-10-12', '微信预约', '已到诊', 17);
INSERT INTO `t_order` VALUES (51, 103, '2019-10-12', '电话预约', '已到诊', 14);
INSERT INTO `t_order` VALUES (52, 104, '2019-10-12', '电话预约', '未到诊', 16);
INSERT INTO `t_order` VALUES (53, 104, '2019-10-12', '微信预约', '未到诊', 17);
INSERT INTO `t_order` VALUES (54, 102, '2019-10-12', '电话预约', '未到诊', 12);
INSERT INTO `t_order` VALUES (55, 101, '2019-10-12', '微信预约', '未到诊', 15);
INSERT INTO `t_order` VALUES (56, 103, '2019-10-12', '电话预约', '已到诊', 18);
INSERT INTO `t_order` VALUES (57, 97, '2019-10-12', '电话预约', '已到诊', 14);
INSERT INTO `t_order` VALUES (58, 97, '2019-10-12', '微信预约', '已到诊', 18);

-- ----------------------------
-- Table structure for t_ordersetting
-- ----------------------------
DROP TABLE IF EXISTS `t_ordersetting`;
CREATE TABLE `t_ordersetting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderDate` date NULL DEFAULT NULL COMMENT '约预日期',
  `number` int(11) NULL DEFAULT NULL COMMENT '可预约人数',
  `reservations` int(11) NULL DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_ordersetting
-- ----------------------------
INSERT INTO `t_ordersetting` VALUES (70, '2019-09-28', 200, 0);
INSERT INTO `t_ordersetting` VALUES (71, '2019-09-29', 150, 150);
INSERT INTO `t_ordersetting` VALUES (72, '2019-09-30', 150, 0);
INSERT INTO `t_ordersetting` VALUES (80, '2019-10-08', 200, 1);
INSERT INTO `t_ordersetting` VALUES (81, '2019-10-09', 200, 200);
INSERT INTO `t_ordersetting` VALUES (82, '2019-10-10', 200, 0);
INSERT INTO `t_ordersetting` VALUES (83, '2019-10-11', 200, 0);
INSERT INTO `t_ordersetting` VALUES (84, '2019-10-12', 180, 27);
INSERT INTO `t_ordersetting` VALUES (85, '2019-10-13', 200, 0);
INSERT INTO `t_ordersetting` VALUES (86, '2019-10-14', 200, 0);
INSERT INTO `t_ordersetting` VALUES (87, '2019-10-15', 200, 1);
INSERT INTO `t_ordersetting` VALUES (88, '2019-10-16', 200, 1);
INSERT INTO `t_ordersetting` VALUES (89, '2019-10-17', 200, 0);
INSERT INTO `t_ordersetting` VALUES (90, '2019-10-18', 200, 0);
INSERT INTO `t_ordersetting` VALUES (91, '2019-10-19', 200, 0);
INSERT INTO `t_ordersetting` VALUES (92, '2019-10-20', 200, 0);
INSERT INTO `t_ordersetting` VALUES (93, '2019-10-21', 200, 0);
INSERT INTO `t_ordersetting` VALUES (94, '2019-10-22', 200, 0);
INSERT INTO `t_ordersetting` VALUES (95, '2019-10-23', 200, 0);
INSERT INTO `t_ordersetting` VALUES (96, '2019-10-24', 200, 0);
INSERT INTO `t_ordersetting` VALUES (97, '2019-10-25', 200, 0);
INSERT INTO `t_ordersetting` VALUES (98, '2019-10-26', 200, 0);
INSERT INTO `t_ordersetting` VALUES (99, '2019-10-27', 200, 0);
INSERT INTO `t_ordersetting` VALUES (100, '2019-10-28', 200, 0);
INSERT INTO `t_ordersetting` VALUES (101, '2019-10-29', 200, 0);
INSERT INTO `t_ordersetting` VALUES (102, '2019-10-30', 200, 0);
INSERT INTO `t_ordersetting` VALUES (103, '2019-10-31', 250, 0);
INSERT INTO `t_ordersetting` VALUES (104, '2019-11-01', 125, 0);
INSERT INTO `t_ordersetting` VALUES (105, '2019-11-02', 110, 0);

-- ----------------------------
-- Table structure for t_package
-- ----------------------------
DROP TABLE IF EXISTS `t_package`;
CREATE TABLE `t_package`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `helpCode` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attention` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_package
-- ----------------------------
INSERT INTO `t_package` VALUES (12, '入职无忧体检套餐（男女通用）', '0001', 'RZTJ', '1', '18-60', 300, '入职体检套餐', '注意啦…..', 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg', '2019-09-24 12:10:20');
INSERT INTO `t_package` VALUES (13, '粉红珍爱(女)升级TM12项筛查体检套餐', '0002', 'FHZA', '2', '18-60', 1200, '本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。', NULL, 'd7114f3d-35bd-4e52-b0b5-9dfc83d54af72.jpg', '2019-10-08 12:10:20');
INSERT INTO `t_package` VALUES (14, '阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐', '0003', 'YGBM', '0', '55-100', 1400, '本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查', NULL, '1291b1fb-40c2-4558-b102-02e05c4cff6c3.jpg', '2019-10-08 12:10:20');
INSERT INTO `t_package` VALUES (15, '珍爱高端升级肿瘤12项筛查（男女单人）', '0004', 'ZAGD', '0', '14-26', 2400, '本套餐是一款针对生化五项检查，心，肝，胆，胃，甲状腺，颈椎，肺功能，脑部检查（经颅多普勒）以及癌症筛查，适合大众人群体检的套餐', NULL, '68c7c13f-8fc2-46c3-b5d6-f7ec7992dc6e1.jpg', '2019-10-09 12:10:20');
INSERT INTO `t_package` VALUES (16, '关爱绿丝带', '0005', 'LOVE', '0', '18-70', 2456, '共同行动', '啦啦啦...', 'e5c9cfef-95db-42a1-8768-dd43bf5b34cf.jpg', '2019-10-10 12:10:20');
INSERT INTO `t_package` VALUES (17, '基因检测', '0006', 'jyjc', '0', '20-40', 588, '啦啦啦啦', '无', '8b5fb506-2354-4db9-8514-d8cd3198f191.jpg', '2019-10-08 12:10:20');
INSERT INTO `t_package` VALUES (18, '孕前检查', '0007', 'yqjc', '2', '22-40', 499, '呵呵呵呵', '布吉岛', '13cdd6a8-26a5-45a9-b48e-1aa80a974bea.jpg', '2019-10-08 12:10:20');

-- ----------------------------
-- Table structure for t_package_checkgroup
-- ----------------------------
DROP TABLE IF EXISTS `t_package_checkgroup`;
CREATE TABLE `t_package_checkgroup`  (
  `package_id` int(11) NOT NULL DEFAULT 0,
  `checkgroup_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`package_id`, `checkgroup_id`) USING BTREE,
  INDEX `checkgroup_key`(`checkgroup_id`) USING BTREE,
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `package_key` FOREIGN KEY (`package_id`) REFERENCES `t_package` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_package_checkgroup
-- ----------------------------
INSERT INTO `t_package_checkgroup` VALUES (12, 5);
INSERT INTO `t_package_checkgroup` VALUES (17, 5);
INSERT INTO `t_package_checkgroup` VALUES (12, 6);
INSERT INTO `t_package_checkgroup` VALUES (12, 7);
INSERT INTO `t_package_checkgroup` VALUES (16, 7);
INSERT INTO `t_package_checkgroup` VALUES (18, 7);
INSERT INTO `t_package_checkgroup` VALUES (12, 8);
INSERT INTO `t_package_checkgroup` VALUES (18, 8);
INSERT INTO `t_package_checkgroup` VALUES (12, 9);
INSERT INTO `t_package_checkgroup` VALUES (12, 10);
INSERT INTO `t_package_checkgroup` VALUES (14, 10);
INSERT INTO `t_package_checkgroup` VALUES (15, 10);
INSERT INTO `t_package_checkgroup` VALUES (17, 10);
INSERT INTO `t_package_checkgroup` VALUES (12, 11);
INSERT INTO `t_package_checkgroup` VALUES (14, 11);
INSERT INTO `t_package_checkgroup` VALUES (15, 11);
INSERT INTO `t_package_checkgroup` VALUES (14, 12);
INSERT INTO `t_package_checkgroup` VALUES (15, 12);
INSERT INTO `t_package_checkgroup` VALUES (16, 12);
INSERT INTO `t_package_checkgroup` VALUES (17, 12);
INSERT INTO `t_package_checkgroup` VALUES (14, 13);
INSERT INTO `t_package_checkgroup` VALUES (15, 13);
INSERT INTO `t_package_checkgroup` VALUES (13, 14);
INSERT INTO `t_package_checkgroup` VALUES (15, 14);
INSERT INTO `t_package_checkgroup` VALUES (18, 14);
INSERT INTO `t_package_checkgroup` VALUES (13, 15);
INSERT INTO `t_package_checkgroup` VALUES (16, 15);
INSERT INTO `t_package_checkgroup` VALUES (17, 15);
INSERT INTO `t_package_checkgroup` VALUES (18, 23);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '新增检查项', 'CHECKITEM_ADD', NULL);
INSERT INTO `t_permission` VALUES (2, '删除检查项', 'CHECKITEM_DELETE', NULL);
INSERT INTO `t_permission` VALUES (3, '编辑检查项', 'CHECKITEM_EDIT', NULL);
INSERT INTO `t_permission` VALUES (4, '查询检查项', 'CHECKITEM_QUERY', NULL);
INSERT INTO `t_permission` VALUES (5, '新增检查组', 'CHECKGROUP_ADD', NULL);
INSERT INTO `t_permission` VALUES (6, '删除检查组', 'CHECKGROUP_DELETE', NULL);
INSERT INTO `t_permission` VALUES (7, '编辑检查组', 'CHECKGROUP_EDIT', NULL);
INSERT INTO `t_permission` VALUES (8, '查询检查组', 'CHECKGROUP_QUERY', NULL);
INSERT INTO `t_permission` VALUES (9, '新增套餐', 'SETMEAL_ADD', NULL);
INSERT INTO `t_permission` VALUES (10, '删除套餐', 'SETMEAL_DELETE', NULL);
INSERT INTO `t_permission` VALUES (11, '编辑套餐', 'SETMEAL_EDIT', NULL);
INSERT INTO `t_permission` VALUES (12, '查询套餐', 'SETMEAL_QUERY', NULL);
INSERT INTO `t_permission` VALUES (13, '预约设置', 'ORDERSETTING', NULL);
INSERT INTO `t_permission` VALUES (14, '查看统计报表', 'REPORT_VIEW', NULL);
INSERT INTO `t_permission` VALUES (15, '新增菜单', 'MENU_ADD', NULL);
INSERT INTO `t_permission` VALUES (16, '删除菜单', 'MENU_DELETE', NULL);
INSERT INTO `t_permission` VALUES (17, '编辑菜单', 'MENU_EDIT', NULL);
INSERT INTO `t_permission` VALUES (18, '查询菜单', 'MENU_QUERY', NULL);
INSERT INTO `t_permission` VALUES (19, '新增角色', 'ROLE_ADD', NULL);
INSERT INTO `t_permission` VALUES (20, '删除角色', 'ROLE_DELETE', NULL);
INSERT INTO `t_permission` VALUES (21, '编辑角色', 'ROLE_EDIT', NULL);
INSERT INTO `t_permission` VALUES (22, '查询角色', 'ROLE_QUERY', NULL);
INSERT INTO `t_permission` VALUES (23, '新增用户', 'USER_ADD', NULL);
INSERT INTO `t_permission` VALUES (24, '删除用户', 'USER_DELETE', NULL);
INSERT INTO `t_permission` VALUES (25, '编辑用户', 'USER_EDIT', NULL);
INSERT INTO `t_permission` VALUES (26, '查询用户', 'USER_QUERY', NULL);

-- ----------------------------
-- Table structure for t_removeimgtime
-- ----------------------------
DROP TABLE IF EXISTS `t_removeimgtime`;
CREATE TABLE `t_removeimgtime`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_removeimgtime
-- ----------------------------
INSERT INTO `t_removeimgtime` VALUES (2, '2019-10-13 01:41:02');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', 'ROLE_ADMIN', NULL);
INSERT INTO `t_role` VALUES (2, '健康管理师', 'ROLE_HEALTH_MANAGER', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `FK_Reference_10`(`menu_id`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (2, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (2, 6);
INSERT INTO `t_role_menu` VALUES (1, 7);
INSERT INTO `t_role_menu` VALUES (2, 7);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (2, 8);
INSERT INTO `t_role_menu` VALUES (1, 9);
INSERT INTO `t_role_menu` VALUES (2, 9);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (2, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (2, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 23);
INSERT INTO `t_role_menu` VALUES (2, 23);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FK_Reference_12`(`permission_id`) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1);
INSERT INTO `t_role_permission` VALUES (1, 2);
INSERT INTO `t_role_permission` VALUES (1, 3);
INSERT INTO `t_role_permission` VALUES (2, 3);
INSERT INTO `t_role_permission` VALUES (1, 4);
INSERT INTO `t_role_permission` VALUES (2, 4);
INSERT INTO `t_role_permission` VALUES (1, 5);
INSERT INTO `t_role_permission` VALUES (2, 5);
INSERT INTO `t_role_permission` VALUES (1, 6);
INSERT INTO `t_role_permission` VALUES (1, 7);
INSERT INTO `t_role_permission` VALUES (1, 8);
INSERT INTO `t_role_permission` VALUES (2, 8);
INSERT INTO `t_role_permission` VALUES (1, 9);
INSERT INTO `t_role_permission` VALUES (2, 9);
INSERT INTO `t_role_permission` VALUES (1, 10);
INSERT INTO `t_role_permission` VALUES (1, 11);
INSERT INTO `t_role_permission` VALUES (2, 11);
INSERT INTO `t_role_permission` VALUES (1, 12);
INSERT INTO `t_role_permission` VALUES (2, 12);
INSERT INTO `t_role_permission` VALUES (1, 13);
INSERT INTO `t_role_permission` VALUES (2, 13);
INSERT INTO `t_role_permission` VALUES (1, 14);
INSERT INTO `t_role_permission` VALUES (2, 14);
INSERT INTO `t_role_permission` VALUES (1, 15);
INSERT INTO `t_role_permission` VALUES (1, 16);
INSERT INTO `t_role_permission` VALUES (1, 17);
INSERT INTO `t_role_permission` VALUES (1, 18);
INSERT INTO `t_role_permission` VALUES (1, 19);
INSERT INTO `t_role_permission` VALUES (1, 20);
INSERT INTO `t_role_permission` VALUES (1, 21);
INSERT INTO `t_role_permission` VALUES (1, 22);
INSERT INTO `t_role_permission` VALUES (1, 23);
INSERT INTO `t_role_permission` VALUES (1, 24);
INSERT INTO `t_role_permission` VALUES (1, 25);
INSERT INTO `t_role_permission` VALUES (1, 26);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` date NULL DEFAULT NULL,
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `station` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, NULL, NULL, 'admin', '$2a$10$wsCqKDdeN5OkBo/KdUNNze8hedJf7jYyoHWNnVeM.oyT/t5HR8HXe', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (4, NULL, NULL, 'xiaohua', '$2a$10$DpD1YtM9Ii1zZ9d/lB0pFu8hm7S4z2LO2QzdQj.At2FyAG21FAVl.', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_Reference_8`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (4, 2);

SET FOREIGN_KEY_CHECKS = 1;
