--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-08-13 15:04:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16594)
-- Name: boardgame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boardgame (
    boardgame_id integer NOT NULL,
    name character varying(50) NOT NULL,
    publisher character varying(50) NOT NULL,
    reorder_quantity integer NOT NULL,
    order_id integer
);


ALTER TABLE public.boardgame OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16676)
-- Name: boardgame_boardgame_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.boardgame ALTER COLUMN boardgame_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.boardgame_boardgame_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16599)
-- Name: inventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inventory (
    inventory_id integer NOT NULL,
    quantity_available integer NOT NULL,
    warehouse_id integer NOT NULL,
    boardgame_id integer NOT NULL,
    reorder_point integer NOT NULL,
    maximum_stock_level integer NOT NULL,
    minimum_stock_level integer NOT NULL
);


ALTER TABLE public.inventory OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16677)
-- Name: inventory_inventory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.inventory ALTER COLUMN inventory_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.inventory_inventory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16661)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    order_id integer NOT NULL,
    boardgame_id integer NOT NULL,
    warehouse_id integer NOT NULL,
    quantity integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16678)
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."order" ALTER COLUMN order_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 16584)
-- Name: warehouse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouse (
    warehouse_id integer NOT NULL,
    name character varying(50) NOT NULL,
    capacity integer NOT NULL,
    num_items integer NOT NULL,
    order_id integer
);


ALTER TABLE public.warehouse OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16679)
-- Name: warehouse_warehouse_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.warehouse ALTER COLUMN warehouse_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.warehouse_warehouse_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4650 (class 2606 OID 16588)
-- Name: warehouse pk_1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse
    ADD CONSTRAINT pk_1 PRIMARY KEY (warehouse_id);


--
-- TOC entry 4652 (class 2606 OID 16598)
-- Name: boardgame pk_2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boardgame
    ADD CONSTRAINT pk_2 PRIMARY KEY (boardgame_id);


--
-- TOC entry 4656 (class 2606 OID 16603)
-- Name: inventory pk_3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT pk_3 PRIMARY KEY (inventory_id);


--
-- TOC entry 4658 (class 2606 OID 16665)
-- Name: order pk_4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT pk_4 PRIMARY KEY (order_id);


--
-- TOC entry 4653 (class 1259 OID 16614)
-- Name: fk_1; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_1 ON public.inventory USING btree (warehouse_id);


--
-- TOC entry 4654 (class 1259 OID 16615)
-- Name: fk_2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_2 ON public.inventory USING btree (boardgame_id);


--
-- TOC entry 4659 (class 2606 OID 16680)
-- Name: inventory fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_1 FOREIGN KEY (warehouse_id) REFERENCES public.warehouse(warehouse_id) ON DELETE CASCADE;


--
-- TOC entry 4660 (class 2606 OID 16685)
-- Name: inventory fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_2 FOREIGN KEY (boardgame_id) REFERENCES public.boardgame(boardgame_id) ON DELETE CASCADE;


--
-- TOC entry 4661 (class 2606 OID 16695)
-- Name: order fk_3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk_3 FOREIGN KEY (boardgame_id) REFERENCES public.boardgame(boardgame_id) ON DELETE CASCADE;


--
-- TOC entry 4662 (class 2606 OID 16690)
-- Name: order fk_4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk_4 FOREIGN KEY (warehouse_id) REFERENCES public.warehouse(warehouse_id) ON DELETE CASCADE;


-- Completed on 2024-08-13 15:04:03

--
-- PostgreSQL database dump complete
--

